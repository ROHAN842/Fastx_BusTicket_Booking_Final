package com.hexaware.fastXBus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.hexaware.fastXBus.dto.UserCustomersDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.UserCustomers;



public interface IUserCustomersService {

	public UserCustomers createUser(UserCustomersDTO usercustomerdto);
	public UserCustomers updateUser(UserCustomersDTO usercustomerdto,Long userId);
	public void  deleteUser(Long userId);
	
	public Long getId(String name);
	public UserCustomersDTO getUserById(Long userId);
	public List<UserCustomersDTO>getAllUserCustomers();
	UserCustomers getUserCustomersByBookingId(Long bookingId);
	public String getRoleByfirstName(String firstName);
	

}
