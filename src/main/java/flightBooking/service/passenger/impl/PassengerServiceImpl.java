package flightBooking.service.passenger.impl;

import flightBooking.entity.*;
import flightBooking.exceptions.BookingNotFoundException;
import flightBooking.repository.PassengerRepository;
import flightBooking.repository.TicketRepository;
import flightBooking.service.booking.BookingService;
import flightBooking.service.passenger.PassengerService;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    TicketRepository ticketRepository;

    Booking booking;

    Flight flight;

    private Set<Passenger> passengers;

    public PassengerServiceImpl() {
    }

    public void getAddPassengerForm(Integer id, Model model){
        try {
            booking = bookingService.getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.getMessage();
        }
        flight = booking.getFlight();
        setMessages(model);
    }

    public void getAddPassenger( Integer id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        try {
            booking = bookingService.getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.getMessage();
        }
        addPassengers(request);

        setTotalAmount();

        setMessages2(redirectAttributes);

        MyUser user = userService.getCurrentUser();

        List<Passenger> passengers = booking.getPassengers();

        passengers.forEach(passenger -> {
            passenger.setUser(user);
            passenger.setFlight(flight);
            passengerRepository.save(passenger);
        });

        createTicket(passengers, user);

    }

    private void createTicket(List<Passenger> passengers, MyUser user) {
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0; i < passengers.size(); i++){
            tickets.add(new Ticket());
            ticketRepository.save(tickets.get(i));
        }
        for(int i = 0; i < passengers.size(); i++){
            tickets.get(i).setFlight(flight);
            tickets.get(i).setSeatNo(flight.getAvailableSeats()-i);
            tickets.get(i).setBooking(booking);
            tickets.get(i).setUser(user);
            tickets.get(i).setPassenger(passengers.get(i));
            passengers.get(i).setTicket(tickets.get(i));
            ticketRepository.save(tickets.get(i));
        }
        user.setTickets(tickets);
    }

    private void setMessages2(RedirectAttributes redirectAttributes) {
        int seatsSelected = booking.getBookedBusinessClassSeats() + booking.getBookedEconomyClassSeats();
        int passengersToAdd = seatsSelected - booking.getPassengers().size();
        if ((long) booking.getPassengers().size() == seatsSelected) {
            redirectAttributes.addFlashAttribute("message2", "You have successfully added all passengers!");
        } else if ((long) booking.getPassengers().size() < seatsSelected) {
            redirectAttributes.addFlashAttribute("message3", "You have successfully added " + booking.getPassengers().size() + " passengers. \n You need to add  " + passengersToAdd + " more.");
        }
    }

    private void setTotalAmount() {
        int businessClassTicketsFinalPrice = booking.getBookedBusinessClassSeats() * flight.getBusinessClassTicketPrice();
        int economyClassTicketsFinalPrice = booking.getBookedEconomyClassSeats() * flight.getEconomyClassTicketPrice();
        int checkedBaggagePrice = booking.getCheckedBaggage() * 30;
        booking.setTotalAmount(businessClassTicketsFinalPrice + economyClassTicketsFinalPrice + checkedBaggagePrice);
    }

    private void addPassengers(HttpServletRequest request) {
        flight = booking.getFlight();
        String[] passengerFullNames = request.getParameterValues("passengerFullName");
        String[] passengerEmailAddresses = request.getParameterValues("passengerEmailAddress");
        String[] passengerGenders = request.getParameterValues("passengerGender");
        String[] passengerAges = request.getParameterValues("passengerAge");
        String[] passengerMobileNumbers = request.getParameterValues("passengerMobileNumber");
        String[] passengerAddresses = request.getParameterValues("passengerAddress");
        String[] passengerClassTypes = request.getParameterValues("passengerClassType");
        String[] passengerCheckedBaggage = request.getParameterValues("passengerCheckedBaggage");
        for (int i = 0; i < passengerFullNames.length; i++) {
            booking.addPassenger(passengerFullNames[i], passengerEmailAddresses[i], passengerGenders[i], passengerAges[i],
                    passengerMobileNumbers[i], passengerAddresses[i], passengerClassTypes[i], passengerCheckedBaggage[i]);

        }
    }

    public void setMessages(Model model){
      int seatsSelected = booking.getBookedBusinessClassSeats() + booking.getBookedEconomyClassSeats();
      if (booking.getPassengers().isEmpty()) {
          if (seatsSelected == 1) {
              model.addAttribute("message4", "Please add the passenger!");
          } else {
              model.addAttribute("message1", "Please add " + seatsSelected + " passenger(s)!");
          }
      }
      model.addAttribute("booking", booking);
      model.addAttribute("flight", flight);
      model.addAttribute("condition", seatsSelected > booking.getPassengers().size());
  }

    @Override
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
}
