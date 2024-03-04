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

import com.hexaware.fastXBus.dto.BusesDTO;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.repository.IBusesRepository;

class BusesServiceTest {
	   @Mock
	    private IBusesRepository ibusesRepositoryMock;
	    
	    @Mock
	    PasswordEncoder passwordEncoderMock;
	    
	    @MockBean
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
		private IBusesRepository ibusesRepository;
		
		@InjectMocks
		private BusesService busesService;
		
		@BeforeEach
		public void setup() {
			MockitoAnnotations.openMocks(this);
		}
		

	@Test
	void testCreateBuses() {
		BusesDTO busesDTO = new BusesDTO();
		Buses buses = new Buses();
		buses.setBusId(busesDTO.getBusId());
		buses.setBusNumber(busesDTO.getBusNumber());
		buses.setCapacity(busesDTO.getCapacity());
	
		
		
//		when(passwordEncoderMock.encode(busesDTO.getPassword())).thenReturn("encodedPassword");
		when(ibusesRepositoryMock.save(any(Buses.class))).thenReturn(buses);
		
		Buses createdBuses = busesService.createBuses(busesDTO);
//		verify(passwordEncoderMock)bookings.encode(bookingsDTO.getPassword());
		verify(ibusesRepositoryMock).save(any(Buses.class));
		
		assertEquals(buses.getBusId(), createdBuses.getBusId());

		assertEquals(buses.getBusNumber(), createdBuses.getBusNumber());
		assertEquals(buses.getCapacity(), createdBuses.getCapacity());
	
		
		
	}


	@Test
	void testDeleteBuses() {
		Buses existingBus = new Buses();
		existingBus.setBusId(existingBus.getBusId());
		existingBus.setBusNumber(existingBus.getBusNumber());
		existingBus.setCapacity(existingBus.getCapacity());
	
	
	    
		when(ibusesRepositoryMock.findById(existingBus.getBusId())).thenReturn(Optional.of(existingBus));
		busesService.deleteBuses(existingBus.getBusId());
		
		verify(ibusesRepositoryMock).findById(existingBus.getBusId());
		verify(ibusesRepositoryMock).delete(existingBus);
		
	}
	
	@Test
	void testGetBusesById () {
		Buses existingBus = new Buses();
		existingBus.setBusId(existingBus.getBusId());
		existingBus.setBusNumber(existingBus.getBusNumber());
		existingBus.setCapacity(existingBus.getCapacity());
	
	
		when(ibusesRepositoryMock.findById(existingBus.getBusId())).thenReturn(Optional.of(existingBus));
		
		BusesDTO busesDTO = busesService.getBusById(existingBus.getBusId());
		verify(ibusesRepositoryMock).findById(existingBus.getBusId());
		
		assertEquals(existingBus.getBusId(), busesDTO.getBusId());

		assertEquals(existingBus.getBusNumber(), busesDTO.getBusNumber());
		assertEquals(existingBus.getCapacity(), busesDTO.getCapacity());
		

    }
 }
	


