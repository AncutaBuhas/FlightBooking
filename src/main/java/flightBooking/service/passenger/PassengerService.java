package flightBooking.service.passenger;

import flightBooking.entity.Passenger;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public interface PassengerService {

    void addPassenger(Passenger passenger);

    void getAddPassengerForm(Integer id, Model model);

    void getAddPassenger(Integer id, HttpServletRequest request, RedirectAttributes redirectAttributes);

}
