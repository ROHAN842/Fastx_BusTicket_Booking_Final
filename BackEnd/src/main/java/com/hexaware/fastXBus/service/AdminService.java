package com.hexaware.fastXBus.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.AdminDTO;
import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.repository.IAdminRepository;


@Service
public class AdminService implements IAdminService {
	@Autowired
	IAdminRepository repository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


	@Override
	public Admin createAdmin(AdminDTO admindto) {
		
		Admin admin=new Admin();
		
		admin.setFirstName(admindto.getFirstName());
		admin.setLastName(admindto.getLastName());
		admin.setEmail(admindto.getEmail());
		admin.setPassword(passwordEncoder.encode(admindto.getPassword()));
		admin.setPhoneNumber(admindto.getPhoneNumber());
	
		
		return repository.save(admin);
	}

	@Override
	public Admin updateAdmin(AdminDTO admindto, Long adminId) {
	    // Retrieve the existing admin by ID
	    Optional<Admin> adminOptional = repository.findById(adminId);
	    
	    if (adminOptional.isPresent()) {
	        Admin existingAdmin = adminOptional.get();
	        
	        // Update fields from the DTO
	        existingAdmin.setFirstName(admindto.getFirstName());
	        existingAdmin.setLastName(admindto.getLastName());
	        existingAdmin.setEmail(admindto.getEmail());
	        existingAdmin.setPassword(passwordEncoder.encode(admindto.getPassword()));
	        existingAdmin.setPhoneNumber(admindto.getPhoneNumber());
	       
	        
	        // Save the updated admin
	        return repository.save(existingAdmin);
	    } else {
	    	logger.error("Admin not found");
	        return null;
	    }
	}


	@Override
	public void deleteAdmin(Long adminId) {
	
		Admin admin = repository.findById(adminId).orElse(null);
		repository.deleteById(adminId);
		
	}

	@Override
	public AdminDTO getAdmin(Long adminId) {
		Admin admin=repository.findById(adminId).orElse(new Admin());
		
		return new AdminDTO(admin.getAdminId(),admin.getFirstName(),admin.getLastName(),admin.getEmail(),admin.getPassword(),admin.getPhoneNumber());
	}

	@Override
	public List<Admin> getAllAdmin() {
		
	
		return repository.findAll(Sort.by("firstName"));
	}

	@Override
	public Optional<Admin> findByfirstName(String firstName) {

		return repository.findByfirstName(firstName);
	}

	@Override
	public boolean checkIfAdminExists(String email) {
		// TODO Auto-generated method stub
		return repository.existsByEmail(email);
	}

	
	

	
	
}
