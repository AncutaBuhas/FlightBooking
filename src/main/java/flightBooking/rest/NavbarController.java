package flightBooking.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping(value = "/")
    public String getHomePageForm(){
        return "home";
    }

    @GetMapping(value = "/contact")
    public String getContactForm(){
        return "contact";
    }

    @GetMapping(value = "/aboutUs")
    public String getAboutUsForm(){
        return "about-us";
    }
}
