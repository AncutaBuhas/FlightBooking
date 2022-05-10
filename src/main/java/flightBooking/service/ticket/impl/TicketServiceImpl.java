package flightBooking.service.ticket.impl;

import flightBooking.entity.Booking;
import flightBooking.entity.Flight;
import flightBooking.entity.Passenger;
import flightBooking.entity.Ticket;
import flightBooking.exceptions.BookingNotFoundException;
import flightBooking.service.booking.BookingService;
import flightBooking.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    BookingService bookingService;

    Booking booking;

    public void getTicketForm(Integer id, Model model){
        try {
           booking = bookingService.getBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.getMessage();
        }
        List<Passenger> passengers = booking.getPassengers();
        Flight flight = booking.getFlight();
        List<Ticket> tickets = booking.getPassengers().stream().map(Passenger::getTicket).toList();
        model.addAttribute("booking", booking);
        model.addAttribute("passengers", passengers);
        model.addAttribute("tickets", tickets);
        model.addAttribute("flight", flight);
    }
}
