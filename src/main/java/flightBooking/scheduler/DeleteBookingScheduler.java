package flightBooking.scheduler;

import flightBooking.repository.BookingRepository;
import flightBooking.repository.FlightRepository;
import flightBooking.repository.PassengerRepository;
import flightBooking.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteBookingScheduler {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TicketRepository ticketRepository;

//    @Scheduled(cron = "0 */2 * * * *")
//    public void deleteBookingIfNotPaid(){
//        List<Booking> bookings = bookingRepository.findAll();
//        bookings.forEach(booking1 -> {
//            LocalDateTime periodToPay = booking1.getBookingDateAndTime().plusMinutes(3);
//            if(Duration.between(LocalDateTime.now(), periodToPay).isNegative() & booking1.getIsPaid().equals(false)){
//                Flight flight = booking1.getFlight();
//                flight.setEconomyClassSeats(flight.getEconomyClassSeats() + booking1.getBookedEconomyClassSeats());
//                flight.setBusinessClassSeats(flight.getBusinessClassSeats() + booking1.getBookedBusinessClassSeats());
//                flight.setAvailableSeats(flight.getEconomyClassSeats() + flight.getBusinessClassSeats());
//                flightRepository.save(flight);
//                booking1.getPassengers().stream().map(Passenger::getTicket).forEach(ticket -> ticketRepository.delete(ticket));
//                booking1.getPassengers().forEach(passenger -> passengerRepository.delete(passenger));
//                bookingRepository.delete(booking1);
//            }
//        });
//    }

}
