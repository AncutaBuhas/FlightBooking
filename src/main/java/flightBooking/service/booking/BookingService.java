package flightBooking.service.booking;

import flightBooking.entity.Booking;
import flightBooking.exceptions.BookingNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface BookingService {

    Booking getBookingByUser();

    Booking getBookingById(Integer id) throws BookingNotFoundException;

    void getBookingCloneForm(Integer id, Model model);

    void getBookingClone(Integer id, Model model);

    void getBookingForm(Integer id, Model model);

    void getBooking(Integer id, Booking newBooking);

    void getPaymentForm(Integer id, Model model);

    void getPayment(Integer id, RedirectAttributes redirectAttributes);

}
