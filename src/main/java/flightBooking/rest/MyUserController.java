package flightBooking.rest;

import flightBooking.entity.MyUser;
import flightBooking.repository.UserRepository;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyUserController {

    private MyUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/user/profile")
    public String getUserProfileForm(Model model){
        userService.getUserProfileForm(model);
        return "user-profile";
    }

    @GetMapping(value = "/user/update")
    public String getUserUpdateForm(Model model) {
     userService.getUserUpdateForm(model);
        return "user-update";
    }

    @PostMapping(value = "/user/update/")
    public String updateUser(@ModelAttribute MyUser newUser, RedirectAttributes redirectAttributes) {
      userService.userUpdate(newUser, redirectAttributes);
        redirectAttributes.addFlashAttribute("message", "The user has been updated successfully.");
        return "user-update";
    }

    @GetMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/bookings/all";
    }

    @GetMapping(value="/user/bookings/all")
    public String AllUsersBookingsForm(Model model){
       userService.getUserAllBookingsForm(model);
        return "user-bookings";
    }

    @GetMapping(value = "/user/bookings/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer id) {
        userService.deleteUserBooking(id);
        return "redirect:/user/bookings/all";
    }

    public void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        currentUser = userRepository.findByUsernameIgnoreCase(currentUserName);
    }
}
