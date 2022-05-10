package flightBooking.rest;

import flightBooking.entity.Booking;
import flightBooking.entity.Flight;
import flightBooking.entity.MyUser;
import flightBooking.service.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FlightController {

    private MyUser currentUser;

    private Booking booking;

    @Autowired
    private FlightService flightService;


    @GetMapping(value = "/flight/all")
    public String getAllFlights(Model model) {
        flightService.getAllFlights(model);
        return "all-flights";
    }

    @GetMapping(value = "/flight/save")
    public String saveFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "add-flight";
    }

    @PostMapping(value = "/flight/save")
    public String saveFlight(@ModelAttribute("flight") Flight flight, RedirectAttributes redirectAttributes) {
       flightService.saveFlight(flight, redirectAttributes);
        return "redirect:/flight/save";
    }

    @GetMapping(value = "/flight/update/{id}")
    public String updateFlightForm(@PathVariable("id") Integer id, Model model) {
       flightService.updateFlightForm(id, model);
        return "update-flight";
    }

    @PostMapping(value = "/flight/update/{id}")
    public String updateFlight(@PathVariable("id") Integer id, @ModelAttribute Flight newFlight,
                               RedirectAttributes redirectAttributes) {
        flightService.updateFlight(id, newFlight, redirectAttributes);
            return "redirect:/flight/all";
    }

    @GetMapping(value = "/flight/delete/{id}")
    public String deleteFlight(@PathVariable("id") Integer id) {
        flightService.deleteFlightById(id);
        return "redirect:/flight/all";
    }

    @GetMapping(value = "/flight/info/{id}")
    public String flightInfoForm(@PathVariable("id") Integer id, Model model) {
      flightService.getFlightInfoForm(id, model);
        return "flight-info";
    }

}
