package com.hexaware.fastXBus.service;

import com.hexaware.fastXBus.dto.PasswordDTO;

public interface IPasswordService {
	
	public String updateUserPassword(PasswordDTO passwordDto);
	
	public String updateAdminPassword(PasswordDTO passwordDto);
	

	
	

}
