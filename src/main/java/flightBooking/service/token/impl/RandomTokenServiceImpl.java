package flightBooking.service.token.impl;

import com.fasterxml.uuid.Generators;
import flightBooking.entity.MyUser;
import flightBooking.service.token.RandomTokenService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomTokenServiceImpl implements RandomTokenService {

    public String randomToken(MyUser user) {
        UUID nameBasedUUID = Generators.nameBasedGenerator().generate(user.getUsername());
        return nameBasedUUID.toString();
    }

}
