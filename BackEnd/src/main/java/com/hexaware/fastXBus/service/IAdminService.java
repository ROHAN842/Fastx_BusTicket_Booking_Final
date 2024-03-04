package com.hexaware.fastXBus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.hexaware.fastXBus.dto.AdminDTO;
import com.hexaware.fastXBus.entity.Admin;






public interface IAdminService {
	public Admin createAdmin(AdminDTO admindto);
	public Admin updateAdmin(AdminDTO admindto,Long bookingId);
	public void  deleteAdmin(Long adminId);
	public AdminDTO getAdmin(Long adminId);
    Optional<Admin> findByfirstName(String firstName);
	public List<Admin>getAllAdmin();
	
	
	

}
