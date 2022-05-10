package flightBooking.rest;

import flightBooking.entity.MyUser;
import flightBooking.entity.Role;
import flightBooking.repository.RoleRepository;
import flightBooking.service.role.RoleService;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        MyUser user = new MyUser();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUser user, Model model) {
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        if (user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
//            user.setRoles(Set.of(new Role("ROLE_USER")));
            user.setRoles(Set.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
            userService.saveUser(user);
            return "register-success";
        } else {
            return "register";
        }
    }

    @GetMapping(value = "/user/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all-users";
    }

}
