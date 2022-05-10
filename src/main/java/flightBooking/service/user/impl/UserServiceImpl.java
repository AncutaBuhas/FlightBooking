package flightBooking.service.user.impl;

import flightBooking.entity.Booking;
import flightBooking.entity.MyUser;
import flightBooking.entity.Role;
import flightBooking.entity.Ticket;
import flightBooking.exceptions.BookingNotFoundException;
import flightBooking.repository.*;
import flightBooking.service.booking.BookingService;
import flightBooking.service.email.BodyBuilderService;
import flightBooking.service.email.EmailSender;
import flightBooking.service.token.RandomTokenService;
import flightBooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private MyUser user;

    private Booking booking;

    @Autowired
    private BodyBuilderService bodyBuilderService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private RandomTokenService randomTokenService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    public MyUser findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public MyUser findUserByUserName(String userName) {
        return userRepository.findByUsernameIgnoreCase(userName);
    }

    public MyUser findUserByRandomToken(String randomToken) {
        return userRepository.findByRandomToken(randomToken);
    }

    public boolean findUserByUserNameAndPassword(String userName, String password) {
        final Optional<MyUser> myUser = Optional.ofNullable(userRepository.findByUsernameIgnoreCase(userName));
        return myUser.filter(user -> BCrypt.checkpw(password, user.getPassword())).isPresent();
    }

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public MyUser saveUser(MyUser u) {
        MyUser user = new MyUser(u);
        user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        user.setRandomToken(randomTokenService.randomToken(u));
        emailSender.sendEmail(user.getEmail(), "Activate your Account", bodyBuilderService.emailBody(user));
        try {
            u.getRoles().forEach(role -> {
                final Role roleByName = roleRepository.findByName(role.getName());
                if (roleByName == null)
                    user.getRoles().add(roleRepository.save(role));
                else {
                    role.setId(roleByName.getId());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }

    public MyUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
       return userRepository.findByUsernameIgnoreCase(currentUserName);
    }

    public void getUserProfileForm(Model model){
        user = getCurrentUser();
        model.addAttribute("user", user);
    }

    public void getUserUpdateForm(Model model){
        user = getCurrentUser();
        model.addAttribute("user", user);
    }

    public void userUpdate(MyUser newUser, RedirectAttributes redirectAttributes){
        user = getCurrentUser();
        try {
            if (newUser.getFullName() != null)
                user.setFullName(newUser.getFullName());
            if (newUser.getUsername() != null)
                user.setUsername(newUser.getUsername());
            if (newUser.getEmail() != null)
                user.setEmail(newUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "The user has been updated successfully.");
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void getUserAllBookingsForm(Model model){
        user = getCurrentUser();
        List<Booking> bookings = user.getBookings();
        model.addAttribute("bookings", bookings);
    }

    public void deleteUserBooking(Integer id){
        try {
            booking = getUserBookingById(id);
        } catch (BookingNotFoundException bnfe) {
            bnfe.printStackTrace();
        }
        booking.getPassengers().forEach(passenger -> {
            Ticket ticket = passenger.getTicket();
            ticketRepository.delete(ticket);
            passengerRepository.delete(passenger);
        });
        bookingRepository.delete(booking);
    }
    public Booking getUserBookingById(Integer id) throws BookingNotFoundException {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return booking.get();
        }
        throw new BookingNotFoundException("Could not find any flight with id = " + id);
    }


}
