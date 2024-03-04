package com.hexaware.fastXBus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Sort;
import com.hexaware.fastXBus.dto.AdminDTO;
import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.repository.IAdminRepository;

class AdminServiceTest {
	@Mock
	private IAdminRepository iadminRepositoryMock;
	
	@Mock PasswordEncoder passwordEncoderMock;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAdminRepository iadminRepository;
	
	@InjectMocks
	private AdminService adminService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreateAdmin() {
		AdminDTO adminDTO=new AdminDTO();
		Admin admin = new Admin();
		admin.setAdminId(adminDTO.getAdminId());
		admin.setFirstName(adminDTO.getFirstName());
		admin.setLastName(adminDTO.getLastName());
		admin.setEmail(adminDTO.getEmail());
		admin.setPassword(adminDTO.getPassword());
		admin.setPhoneNumber(adminDTO.getPhoneNumber());
	
		
		when(passwordEncoderMock.encode(adminDTO.getPassword())).thenReturn("encodedPassword");
		when(iadminRepositoryMock.save(any(Admin.class))).thenReturn(admin);
		
		Admin createdAdmin = adminService.createAdmin(adminDTO);
		
		verify(passwordEncoderMock).encode(adminDTO.getPassword());
		verify(iadminRepositoryMock).save(any(Admin.class));
		
		assertEquals(admin.getAdminId(), createdAdmin.getAdminId());
		assertEquals(admin.getFirstName(), createdAdmin.getFirstName());
		assertEquals(admin.getLastName(), createdAdmin.getLastName());
		assertEquals(admin.getEmail(), createdAdmin.getEmail());
		assertEquals(admin.getPassword(), createdAdmin.getPassword());
		assertEquals(admin.getPhoneNumber(), createdAdmin.getPhoneNumber());
		assertEquals(admin.getGetRole(), createdAdmin.getGetRole());
	}
	
	@Test
	void testDeleteAdmin() {
		Admin existingAdmin = new Admin();
		existingAdmin.setAdminId(existingAdmin.getAdminId());
		existingAdmin.setFirstName(existingAdmin.getFirstName());
		existingAdmin.setLastName(existingAdmin.getLastName());
		existingAdmin.setEmail(existingAdmin.getEmail());
		existingAdmin.setPassword(existingAdmin.getPassword());
		existingAdmin.setPhoneNumber(existingAdmin.getPhoneNumber());
	    
		when(iadminRepositoryMock.findById(existingAdmin.getAdminId())).thenReturn(Optional.of(existingAdmin));
		adminService.deleteAdmin(existingAdmin.getAdminId());
		
		verify(iadminRepositoryMock).findById(existingAdmin.getAdminId());
		verify(iadminRepositoryMock).delete(existingAdmin);
		
	    
	}

	@Test
	void testGetAdminById() {
		Admin existingAdmin = new Admin();
		existingAdmin.setAdminId(existingAdmin.getAdminId());
		existingAdmin.setFirstName(existingAdmin.getFirstName());
		existingAdmin.setLastName(existingAdmin.getLastName());
		existingAdmin.setEmail(existingAdmin.getEmail());
		existingAdmin.setPassword(existingAdmin.getPassword());
		existingAdmin.setPhoneNumber(existingAdmin.getPhoneNumber());

		when(iadminRepositoryMock.findById(existingAdmin.getAdminId())).thenReturn(Optional.of(existingAdmin));

		AdminDTO adminDTO = adminService.getAdmin(existingAdmin.getAdminId());

		verify(iadminRepositoryMock).findById(existingAdmin.getAdminId());

		assertEquals(existingAdmin.getAdminId(), adminDTO.getAdminId());
		assertEquals(existingAdmin.getFirstName(), adminDTO.getFirstName());
		assertEquals(existingAdmin.getLastName(), adminDTO.getLastName());
		assertEquals(existingAdmin.getEmail(), adminDTO.getEmail());
		assertEquals(existingAdmin.getPassword(), adminDTO.getPassword());
		assertEquals(existingAdmin.getPhoneNumber(), adminDTO.getPhoneNumber());
	}

	@Test
	void testgetAllAdmin() {
		 List<Admin> adminList = new ArrayList<>();
		
		 when(iadminRepositoryMock.findAll(Sort.by("firstname"))).thenReturn(adminList);
		 List<Admin> returnedAdmins = adminService.getAllAdmin();
		 verify(iadminRepositoryMock, times(1)).findAll(Sort.by("firstname"));

	        // Verify if the returnedAdmins list matches the expected adminList
	        assertEquals(adminList, returnedAdmins);
		 
	}

}
