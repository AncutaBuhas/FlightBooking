package flightBooking.service.email.impl;

import flightBooking.entity.MyUser;
import flightBooking.service.email.BodyBuilderService;
import flightBooking.service.token.RandomTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodyBuilderServiceImpl implements BodyBuilderService {

    @Autowired
    RandomTokenService randomTokenService;


    @Override
    public String emailBody(MyUser myUser) {
        return "Hello,\n\n" +
                "In order to activate your account please access the following link:\n" +
                "http://localhost:8080/activation/" + myUser.getRandomToken();
    }
}
