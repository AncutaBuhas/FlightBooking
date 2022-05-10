package flightBooking.service.token;

import flightBooking.entity.MyUser;

public interface
RandomTokenService {
    String randomToken(MyUser user);
}
