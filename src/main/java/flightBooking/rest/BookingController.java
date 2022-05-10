package flightBooking.rest;

import flightBooking.entity.Booking;
import flightBooking.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping(value = "/flight/booking-clone/{id}")
    public String getBookingCloneForm(@PathVariable("id") Integer id, Model model) {
        bookingService.getBookingCloneForm(id, model);
        return "book-flight-clone";
    }

    @PostMapping(value = "/flight/booking-clone/{id}")
    public String getBookingClone(@PathVariable("id") Integer id, Model model) {
        bookingService.getBookingClone(id, model);
        Booking booking = bookingService.getBookingByUser();
        return "redirect:/flight/booking/" + booking.getId();
    }

    @GetMapping(value = "/flight/booking/{id}")
    public String getBookingForm(@PathVariable("id") Integer id, Model model) {
       bookingService.getBookingForm(id, model);
        return "book-flight";
    }

    @PostMapping(value = "/flight/booking/{id}")
    public String getBooking(@PathVariable("id") Integer id, Booking newBooking) {
       bookingService.getBooking(id, newBooking);
        return "redirect:/flight/addPassenger/{id}";
    }

    @GetMapping(value = "/flight/payment/{id}")
    public String payBookingForm(@PathVariable("id") Integer id, Model model) {
       bookingService.getPaymentForm(id, model);
        return "payment";
    }

    @PostMapping(value = "/flight/payment/{id}")
    public String payBooking(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
       bookingService.getPayment(id, redirectAttributes);
        return "redirect:/flight/ticket/{id}";
    }

}
