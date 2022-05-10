package flightBooking.rest;

import flightBooking.entity.MyUser;
import flightBooking.repository.UserRepository;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/activation/{randomToken}")
    public String registerForm(@PathVariable("randomToken") String randomToken, Model model) {
        model.addAttribute("user", userService.findUserByRandomToken(randomToken));
        return "login-clone";
    }

    @PostMapping(value = "/activation/{randomToken}")
    public String registerUser(@PathVariable("randomToken") String randomToken) {
        MyUser user = userService.findUserByRandomToken(randomToken);
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/login";
    }

}
