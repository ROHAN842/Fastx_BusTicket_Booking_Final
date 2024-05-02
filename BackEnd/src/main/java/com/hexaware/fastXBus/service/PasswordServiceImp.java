package com.hexaware.fastXBus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.fastXBus.dto.PasswordDTO;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IAdminRepository;
import com.hexaware.fastXBus.repository.IBusOperatorsRepository;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PasswordServiceImp implements IPasswordService {
	
	@Autowired
	IUserCustomersRepository userRepo;
	
	@Autowired
	IAdminRepository adminRepo;
	
	@Autowired
	IBusOperatorsRepository busoperatorRepo;
	
	
	@Autowired
    private IAdminService adminService;
	
	@Autowired
	private IBusOperatorsService busoperatorservice;
	
	@Autowired
	private IUserCustomersService userservice;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    public String updatePassword(PasswordDTO passwordDTO) {
        String email = passwordDTO.getEmail();
        String newPassword = passwordDTO.getNewPassword();

        if (userservice.checkIfUserExists(email)) {
            return updateUserPassword(passwordDTO);
        } else if (adminService.checkIfAdminExists(email)) {
            return updateAdminPassword(passwordDTO);
    
        } else {
            return "User not found";
        }
    }

	@Override
	public String updateUserPassword(PasswordDTO passwordDto) {
		// TODO Auto-generated method stub
		 String email = passwordDto.getEmail();
	        String newPassword = passwordDto.getNewPassword();
	        UserCustomers user = userRepo.findByEmail(email).orElse(null);
	        user.setPassword(passwordEncoder.encode(newPassword));
	        userRepo.save(user);
	        return "User password updated successfully";
	}
    
 


	@Override
	public String updateAdminPassword(PasswordDTO passwordDto) {
		// TODO Auto-generated method stub
		return null;
	}



//	@Override
//	public String updateBusOperatorPassword(PasswordDTO passwordDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
