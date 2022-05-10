package flightBooking.service.booking.impl;

import flightBooking.entity.*;
import flightBooking.exceptions.BookingNotFoundException;
import flightBooking.exceptions.FlightNotFoundException;
import flightBooking.exceptions.NoSeatsAvailableException;
import flightBooking.repository.BookingRepository;
import flightBooking.repository.FlightRepository;
import flightBooking.service.booking.BookingService;
import flightBooking.service.flight.FlightService;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

   @Autowired
   FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    Booking booking;

    Flight flight;

    MyUser user;

    public void getBookingCloneForm(Integer id, Model model) {
        try {
            flight = flightService.getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        user = userService.getCurrentUser();
        booking = new Booking(flight);
        booking.getUsers().add(user);
        booking.setFlight(flight);
        booking.setIsPaid(false);
        bookingRepository.save(booking);
        model.addAttribute("flight", flight);
        model.addAttribute("booking", booking);
    }

    public void getBookingClone(Integer id, Model model) {
        try {
            flight = flightService.getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        user = userService.getCurrentUser();
        booking = getBookingByUser();
        model.addAttribute("flight", flight);
        model.addAttribute("booking", booking);
    }

    public void getBookingForm(Integer id, Model model) {
        try {
            booking = getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.printStackTrace();
        }
        flight = booking.getFlight();
        model.addAttribute("booking", booking);
        model.addAttribute("flight", flight);
        model.addAttribute("businessClassSeats", flight.getBusinessClassSeats());
        model.addAttribute("economyClassSeats", flight.getEconomyClassSeats());
    }

    public void getBooking(Integer id, Booking newBooking) {
        try {
            booking = getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.printStackTrace();
        }
        flight = booking.getFlight();
        try {
            if (newBooking.getBookedBusinessClassSeats() != null)
                booking.setBookedBusinessClassSeats(newBooking.getBookedBusinessClassSeats());

            if (newBooking.getBookedEconomyClassSeats() != null)
                booking.setBookedEconomyClassSeats(newBooking.getBookedEconomyClassSeats());

            if (newBooking.getCheckedBaggage() != null)
                booking.setCheckedBaggage(newBooking.getCheckedBaggage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            noSeatsAvailable();
        } catch (NoSeatsAvailableException nsae) {
            nsae.printStackTrace();
        }
        bookingRepository.save(booking);
    }

    public void getPaymentForm(Integer id, Model model) {
        try {
            booking = getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.printStackTrace();
        }
        Flight flight = booking.getFlight();
        model.addAttribute("booking", booking);
        model.addAttribute("flight", flight);
        model.addAttribute("bookingTotalAmount", booking.getTotalAmount());
    }

    public void getPayment(Integer id, RedirectAttributes redirectAttributes) {
        try {
            booking = getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.printStackTrace();
        }
        Flight flight = booking.getFlight();
        MyUser user = userService.getCurrentUser();
        booking.setIsPaid(true);
        flight.getUsers().add(user);
        flight.setEconomyClassSeats(flight.getEconomyClassSeats() - booking.getBookedEconomyClassSeats());
        flight.setBusinessClassSeats(flight.getBusinessClassSeats() - booking.getBookedBusinessClassSeats());
        flight.setAvailableSeats(flight.getEconomyClassSeats() + flight.getBusinessClassSeats());
        flightRepository.save(flight);
        redirectAttributes.addFlashAttribute("message4", "The flight has been booked successfully.");
    }

    public Booking getBookingByUser() {
        user = userService.getCurrentUser();
        long count = user.getBookings().size();
        return user.getBookings().stream().skip(count - 1).findFirst().get();
    }

    public Booking getBookingById(Integer id) throws BookingNotFoundException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return booking.get();
        }
        throw new BookingNotFoundException("Could not find any flight with id = " + id);
    }

    public void noSeatsAvailable() throws NoSeatsAvailableException {
        if (flight.getAvailableSeats() == 0) {
            throw new NoSeatsAvailableException("No more seats available");
        }
    }

}
