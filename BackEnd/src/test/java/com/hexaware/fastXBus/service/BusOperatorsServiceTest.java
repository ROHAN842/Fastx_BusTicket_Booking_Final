package com.hexaware.fastXBus.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.fastXBus.dto.BusOperatorsDTO;
import com.hexaware.fastXBus.entity.BusOperators;
import com.hexaware.fastXBus.repository.IBusOperatorsRepository;

class BusOperatorsServiceTest {

	  @Mock
	    private IBusOperatorsRepository ibusOperatorsRepositoryMock;
	    
	    @Mock
	    PasswordEncoder passwordEncoderMock;
	    
	    @MockBean
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
		private IBusOperatorsRepository ibusOperatorsRepository;
		
		@InjectMocks
		private BusOperatorsService busOperatorsService;
		
		@BeforeEach
		public void setup() {
			MockitoAnnotations.openMocks(this);
		}
		
		@Test
		void testCreateBusOperators() {
			BusOperatorsDTO busOperatorsDTO = new BusOperatorsDTO();
			BusOperators busOperators = new BusOperators();
			busOperators.setOperatorId(busOperatorsDTO.getOperatorId());
			busOperators.setOperatorName(busOperatorsDTO.getOperatorName());
			busOperators.setPassword(busOperatorsDTO.getPassword());
			busOperators.setContactPhone(busOperatorsDTO.getContactPhone());
			
//			when(passwordEncoderMock.encode(bookingsDTO.getPassword())).thenReturn("encodedPassword");
			when(ibusOperatorsRepositoryMock.save(any(BusOperators.class))).thenReturn(busOperators);
			
			BusOperators createdBusOperators = busOperatorsService.createBusOperators(busOperatorsDTO);
//			verify(passwordEncoderMock)bookings.encode(bookingsDTO.getPassword());
			verify(ibusOperatorsRepositoryMock).save(any(BusOperators.class));
			
			assertEquals(busOperators.getOperatorId(), createdBusOperators.getOperatorId());
			assertEquals(busOperators.getOperatorName(), createdBusOperators.getOperatorName());
			assertEquals(busOperators.getPassword(), createdBusOperators.getPassword());
			assertEquals(busOperators.getContactPhone(), createdBusOperators.getContactPhone());
			
	  	}
		
		@Test
		void testDeleteBusOperators() {
			BusOperators existingBusOperators = new BusOperators();
			existingBusOperators.setOperatorId(existingBusOperators.getOperatorId());
			existingBusOperators.setOperatorName(existingBusOperators.getOperatorName());
			existingBusOperators.setPassword(existingBusOperators.getPassword());
			existingBusOperators.setContactPhone(existingBusOperators.getContactPhone());

		
		    
			when(ibusOperatorsRepositoryMock.findById(existingBusOperators.getOperatorId())).thenReturn(Optional.of(existingBusOperators));
			busOperatorsService.deleteBusOperators(existingBusOperators.getOperatorId());
			
			verify(ibusOperatorsRepositoryMock).findById(existingBusOperators.getOperatorId());
			verify(ibusOperatorsRepositoryMock).delete(existingBusOperators);
			
		    
		}
		
		@Test
		void testGetBusOperatorsById () {
			BusOperators existingBusOperators = new BusOperators();
			existingBusOperators.setOperatorId(existingBusOperators.getOperatorId());
			existingBusOperators.setOperatorName(existingBusOperators.getOperatorName());
			existingBusOperators.setPassword(existingBusOperators.getPassword());
			existingBusOperators.setContactPhone(existingBusOperators.getContactPhone());

		
			when(ibusOperatorsRepositoryMock.findById(existingBusOperators.getOperatorId())).thenReturn(Optional.of(existingBusOperators));
			
			BusOperatorsDTO busOperatorsDTO = busOperatorsService.getBusOperatorsById(existingBusOperators.getOperatorId());
			verify(ibusOperatorsRepositoryMock).findById(existingBusOperators.getOperatorId());
			
			assertEquals(existingBusOperators.getOperatorId(), busOperatorsDTO.getOperatorId());
			assertEquals(existingBusOperators.getOperatorName(), busOperatorsDTO.getOperatorName());
			assertEquals(existingBusOperators.getPassword(), busOperatorsDTO.getPassword());
			assertEquals(existingBusOperators.getContactPhone(), busOperatorsDTO.getContactPhone());


	    }


}
