package flightBooking.rest;

import flightBooking.entity.MyUser;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/first-index"})
    public String firstIndexPage(Model model) {
        MyUser user = userService.getCurrentUser();
        model.addAttribute("username", user.getFullName());
        return "first-index";
    }

    @GetMapping(value = "/index")
    public String indexPage(){
        return "index";
    }

}
