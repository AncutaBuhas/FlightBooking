package flightBooking.rest;

import flightBooking.service.booking.BookingService;
import flightBooking.service.flight.FlightService;
import flightBooking.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightService flightService;

    @Autowired
    BookingService bookingService;


    @GetMapping(value = "/flight/addPassenger/{id}")
    public String addPassengerForm(@PathVariable("id") Integer id, Model model) {
        passengerService.getAddPassengerForm(id, model);
        return "add-passenger";
    }

    @PostMapping(value = "/flight/addPassenger/{id}")
    public String addPassenger(@PathVariable("id") Integer id, HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        passengerService.getAddPassenger(id, request, redirectAttributes);
        return "redirect:/flight/addPassenger/{id}";
    }


}
