package flightBooking.service.email;

import flightBooking.entity.MyUser;

public interface BodyBuilderService {

    String emailBody (MyUser myUser);

}
