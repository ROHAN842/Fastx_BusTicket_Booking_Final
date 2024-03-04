package com.hexaware.fastXBus.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.entity.BusOperators;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IAdminRepository;
import com.hexaware.fastXBus.repository.IBusOperatorsRepository;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserCustomersRepository userrepository;
    @Autowired
    private IAdminRepository adminrepository;
    @Autowired
    private IBusOperatorsRepository busrepository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCustomers> userCustomersInfo = userrepository.findByFirstName(username);
        if (userCustomersInfo.isPresent()) {
            return userCustomersInfo.map(UserInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        }

        Optional<Admin> adminInfo = adminrepository.findByfirstName(username);
        if (adminInfo.isPresent()) {
            return adminInfo.map(AdminInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
        }

        Optional<BusOperators> busOperatorsInfo = busrepository.findByoperatorName(username);
        if (busOperatorsInfo.isPresent()) {
            return busOperatorsInfo.map(BusOperatorInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Bus Operator not found: " + username));
        }

        throw new UsernameNotFoundException("User, Admin, or Bus Operator not found for username: " + username);
    }
   
   
}
