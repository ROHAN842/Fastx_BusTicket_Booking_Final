package com.hexaware.fastXBus.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.AuthRequest;
import com.hexaware.fastXBus.dto.PasswordDTO;
import com.hexaware.fastXBus.dto.UserCustomersDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.exceptions.UserCustomerNotFountException;
import com.hexaware.fastXBus.service.IUserCustomersService;
import com.hexaware.fastXBus.service.JwtService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/usercustomers")
public class UserCustomerRestController {
    @Autowired
    private IUserCustomersService usercustomer;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(UserCustomerRestController.class);

    @PostMapping("/create")
    public ResponseEntity<UserCustomers> createUser(@RequestBody UserCustomersDTO usercustomerdto) {
        logger.info("user created");
        UserCustomers createdUser = usercustomer.createUser(usercustomerdto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<UserCustomers> updateUser(@RequestBody UserCustomersDTO usercustomerdto, @PathVariable Long userId) {
        logger.info("user updated");
        UserCustomers updatedUser = usercustomer.updateUser(usercustomerdto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        logger.info("user deleted");
        usercustomer.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getById/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<UserCustomersDTO> getUserById(@PathVariable Long userId) throws UserCustomerNotFountException {
        if (userId == 0) {
            throw new UserCustomerNotFountException(HttpStatus.BAD_REQUEST, "User not found" + userId);
        }
        UserCustomersDTO userDTO = usercustomer.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserCustomersDTO>> getAllUserCustomers() {
        logger.info("All user");
        List<UserCustomersDTO> allUsers = usercustomer.getAllUserCustomers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<UserCustomers> getUserCustomersByBookingId(@PathVariable Long bookingId) {
        UserCustomers userCustomers = usercustomer.getUserCustomersByBookingId(bookingId);
        return ResponseEntity.ok(userCustomers);
    }
}
