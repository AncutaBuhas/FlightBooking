package flightBooking.service.flight.impl;

import flightBooking.entity.Flight;
import flightBooking.exceptions.FlightNotFoundException;
import flightBooking.repository.FlightRepository;
import flightBooking.service.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    Flight flight;

    public List<Flight> getAllFlights(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        return flights;
    }

    public void saveFlight(Flight flight, RedirectAttributes redirectAttributes) {
        flight.setBusinessClassTicketPrice((int) (flight.getEconomyClassTicketPrice() * 1.6));
        flight.setAvailableSeats(flight.getBusinessClassSeats() + flight.getEconomyClassSeats());
        flight.setBookedSeats(0);
        flightRepository.save(flight);
        redirectAttributes.addFlashAttribute("message", "The flight has been saved successfully.");
    }

    public void updateFlightForm(Integer id, Model model) {
        try {
            flight = getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        model.addAttribute("flight", flight);
    }

    public void updateFlight(Integer id, Flight newFlight, RedirectAttributes redirectAttributes) {
        try {
            flight = getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        try {
            if (newFlight.getDepartureCity() != null)
                flight.setDepartureCity(newFlight.getDepartureCity());
            if (newFlight.getDestinationCity() != null)
                flight.setDestinationCity(newFlight.getDestinationCity());
            if (newFlight.getDepartureDate() != null)
                flight.setDepartureDate(newFlight.getDepartureDate());
            if (newFlight.getDepartureHour() != null)
                flight.setDepartureHour(newFlight.getDepartureHour());
            if (newFlight.getAirlineName() != null)
                flight.setAirlineName(newFlight.getAirlineName());
            if (newFlight.getEconomyClassTicketPrice() != null)
                flight.setEconomyClassTicketPrice(newFlight.getEconomyClassTicketPrice());
            if (newFlight.getBusinessClassSeats() != null)
                flight.setBusinessClassSeats(newFlight.getBusinessClassSeats());
            if (newFlight.getEconomyClassSeats() != null)
                flight.setEconomyClassSeats(newFlight.getEconomyClassSeats());
            if (newFlight.getGateNumber() != null)
                flight.setGateNumber(newFlight.getGateNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        flight.setAvailableSeats(newFlight.getBusinessClassSeats() + newFlight.getEconomyClassSeats());
        flightRepository.save(flight);
        redirectAttributes.addFlashAttribute("message", "The flight has been updated successfully.");
    }

    public void deleteFlightById(Integer id) {
        try {
            flight = getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        flightRepository.deleteById(id);
    }

    public void getFlightInfoForm(Integer id, Model model){
        try {
            flight = getFlightById(id);
        } catch (FlightNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        Flight flight = flightRepository.getById(id);
        if (flight.getAvailableSeats() == 1) {
            model.addAttribute("message1", "Last seat!");
        } else if (flight.getAvailableSeats() == 2) {
            model.addAttribute("message2", "Last 2 seats!");
        } else if (flight.getAvailableSeats() == 3) {
            model.addAttribute("message3", "Last 3 seats!");
        }
        model.addAttribute("flight", flight);
    }


    public Flight getFlightById(Integer id) throws FlightNotFoundException {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            return flight.get();
        }
        throw new FlightNotFoundException("Could not find any flight with id = " + id);
    }


}
