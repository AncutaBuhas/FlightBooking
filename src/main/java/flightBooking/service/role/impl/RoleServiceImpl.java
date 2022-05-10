package flightBooking.service.role.impl;

import flightBooking.entity.Role;
import flightBooking.repository.RoleRepository;
import flightBooking.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role) {
        final Role roleByName = roleRepository.findByName(role.getName());
        if (roleByName == null)
            roleRepository.save(role);
        else {
            role.setId(roleByName.getId());
        }

    }
}
