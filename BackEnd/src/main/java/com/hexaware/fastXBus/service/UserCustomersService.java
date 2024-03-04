package com.hexaware.fastXBus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.UserCustomersDTO;
import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.entity.BusOperators;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IAdminRepository;
import com.hexaware.fastXBus.repository.IBusOperatorsRepository;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;


@Service
public class UserCustomersService implements IUserCustomersService {

	
	@Autowired
	IAdminRepository arepository;
	@Autowired
	IBusOperatorsRepository brepository;
	@Autowired
	IUserCustomersRepository repository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserCustomersService.class);
	@Override
	public UserCustomers createUser(UserCustomersDTO usercustomerdto) {
		UserCustomers usercustomer=new UserCustomers();
		usercustomer.setFirstName(usercustomerdto.getFirstName());
		usercustomer.setLastName(usercustomerdto.getLastName());
		usercustomer.setEmail(usercustomerdto.getEmail());
		usercustomer.setPassword(passwordEncoder.encode(usercustomerdto.getPassword()));
		usercustomer.setPhoneNumber(usercustomerdto.getPhoneNumber());
		usercustomer.setAddress(usercustomerdto.getAddress());
		usercustomer.setState(usercustomerdto.getState());
		usercustomer.setCity(usercustomerdto.getCity());
		usercustomer.setZipCode(usercustomerdto.getZipCode());
		return repository.save(usercustomer);
		
	}

	@Override
	public UserCustomers updateUser(UserCustomersDTO usercustomerdto, Long userId) {
	  
	    Optional<UserCustomers> userOptional = repository.findById(userId);
	    
	    if (userOptional.isPresent()) {
	     
	        UserCustomers existingUser = userOptional.get();
	        
	        
	        existingUser.setFirstName(usercustomerdto.getFirstName());
	        existingUser.setLastName(usercustomerdto.getLastName());
	        existingUser.setEmail(usercustomerdto.getEmail());
	        existingUser.setPhoneNumber(usercustomerdto.getPhoneNumber());
	        existingUser.setAddress(usercustomerdto.getAddress());
	        existingUser.setState(usercustomerdto.getState());
	        existingUser.setCity(usercustomerdto.getCity());
	        existingUser.setZipCode(usercustomerdto.getZipCode());
	        
	        
	        return repository.save(existingUser);
	    } else {
	    	logger.error("user not found");
	        return null;
	    }
	}

	@Override
	public void deleteUser(Long userId) {
		repository.deleteById(userId);
		
	}

	@Override
	public UserCustomersDTO getUserById(Long userId) {
		UserCustomers usercustomers=repository.findById(userId).orElse(new UserCustomers());
		return new UserCustomersDTO(usercustomers.getUserId(),usercustomers.getFirstName(),usercustomers.getLastName(),usercustomers.getEmail(),usercustomers.getPassword(),usercustomers.getPhoneNumber(),usercustomers.getAddress(),usercustomers.getCity(),usercustomers.getState(),usercustomers.getZipCode());
	}

	@Override
	public List<UserCustomersDTO> getAllUserCustomers() {
	    List<UserCustomers> userCustomersList = repository.findAll(Sort.by("firstName"));
	    List<UserCustomersDTO> userCustomersDTOList = new ArrayList<>();

	    for (UserCustomers userCustomers : userCustomersList) {
	        UserCustomersDTO userCustomersDTO = new UserCustomersDTO(
	            userCustomers.getUserId(),
	            userCustomers.getFirstName(),
	            userCustomers.getLastName(),
	            userCustomers.getEmail(),
	            userCustomers.getPassword(),
	            userCustomers.getPhoneNumber(),
	            userCustomers.getAddress(),
	            userCustomers.getCity(),
	            userCustomers.getState(),
	            userCustomers.getZipCode()
	        );
	        userCustomersDTOList.add(userCustomersDTO);
	    }
	    return userCustomersDTOList;
	}


	@Override
	public UserCustomers getUserCustomersByBookingId(Long bookingId) {
		// TODO Auto-generated method stub
		return repository.findByBookingsBookingId(bookingId);
	}

	@Override
	public String getRoleByfirstName(String firstName) {
	    // Check Admin repository first
	    Optional<Admin> adminOptional = arepository.findByfirstName(firstName);
	    if (adminOptional.isPresent()) {
	        // Admin found, retrieve the role
	        Admin admin = adminOptional.get();
	        return admin.getGetRole(); // Assuming there's a method to retrieve the role from the Admin entity
	    }

	    // If not found in Admin repository, check BusOperators repository
	    Optional<BusOperators> busOperatorOptional = brepository.findByoperatorName(firstName);
	    if (busOperatorOptional.isPresent()) {
	        // BusOperator found, retrieve the role
	        BusOperators busOperator = busOperatorOptional.get();
	        return busOperator.getRole(); // Assuming there's a method to retrieve the role from the BusOperators entity
	    }

	    // If not found in BusOperators repository, check UserCustomers repository
	    Optional<UserCustomers> userCustomersOptional = repository.findByFirstName(firstName);
	    if (userCustomersOptional.isPresent()) {
	        // UserCustomers found, retrieve the role
	        UserCustomers userCustomers = userCustomersOptional.get();
	        return userCustomers.getRole(); // Assuming there's a method to retrieve the role from the UserCustomers entity
	    }

	    // User not found in any repository
	    logger.error("User not found with first name: " + firstName);
	    return null;
	}

	@Override
	public Long getId(String name) {
	    // Check Admin repository first
	    Optional<Admin> adminOptional = arepository.findByfirstName(name);
	    if (adminOptional.isPresent()) {
	        // Admin found, return the ID
	        return adminOptional.get().getAdminId(); // Assuming there's a method to retrieve the ID from the Admin entity
	    }

	    // If not found in Admin repository, check BusOperators repository
	    Optional<BusOperators> busOperatorOptional = brepository.findByoperatorName(name);
	    if (busOperatorOptional.isPresent()) {
	        // BusOperator found, return the ID
	        return busOperatorOptional.get().getOperatorId(); // Assuming there's a method to retrieve the ID from the BusOperators entity
	    }

	    // If not found in BusOperators repository, check UserCustomers repository
	    Optional<UserCustomers> userCustomersOptional = repository.findByFirstName(name);
	    if (userCustomersOptional.isPresent()) {
	        // UserCustomers found, return the ID
	        return userCustomersOptional.get().getUserId(); // Assuming there's a method to retrieve the ID from the UserCustomers entity
	    }

	    // Entity not found in any repository
	    logger.error("Entity not found with name: " + name);
	    return null;
	}


	

	

}
