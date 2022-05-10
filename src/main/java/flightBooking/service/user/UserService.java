package flightBooking.service.user;

import flightBooking.entity.MyUser;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface UserService {

    MyUser findUserByEmail(String email);

    MyUser findUserByUserName(String userName);

    MyUser findUserByRandomToken(String randomToken);

    boolean findUserByUserNameAndPassword(String userName, String password);

    List<MyUser> findAll();

    void deleteById(long id);

    MyUser saveUser(MyUser u);

    MyUser getCurrentUser();

    void getUserProfileForm(Model model);

    void getUserUpdateForm(Model model);

    void userUpdate(MyUser newUser, RedirectAttributes redirectAttributes);

    void deleteUser(Long id);

    void getUserAllBookingsForm(Model model);

    void deleteUserBooking(Integer id);
}
