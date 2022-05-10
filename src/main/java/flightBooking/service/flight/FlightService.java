package flightBooking.service.flight;

import flightBooking.entity.Flight;
import flightBooking.exceptions.FlightNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights(Model model);

    Flight getFlightById(Integer id) throws FlightNotFoundException;

    void saveFlight(Flight flight, RedirectAttributes redirectAttributes);

    void updateFlightForm(Integer id, Model model);

    void updateFlight(Integer id, Flight newFlight, RedirectAttributes redirectAttributes);

    void deleteFlightById(Integer id);

    void getFlightInfoForm(Integer id, Model model);

}
