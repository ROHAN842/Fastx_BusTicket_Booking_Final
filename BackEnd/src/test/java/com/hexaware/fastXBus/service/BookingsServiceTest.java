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

import com.hexaware.fastXBus.dto.BookingsDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.repository.IBookingsRepository;

class BookingsServiceTest {
    @Mock
    private IBookingsRepository ibookingsRepositoryMock;
    
    @Mock
    PasswordEncoder passwordEncoderMock;
    
    @MockBean
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	private IBookingsRepository ibookingsRepository;
	
	@InjectMocks
	private BookingsService bookingsService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
//	
//	@Test
//	void testCreateBookings() {
//		BookingsDTO bookingsDTO = new BookingsDTO();
//		Bookings bookings = new Bookings();
//		bookings.setBookingId(bookingsDTO.getBookingId());
//		bookings.setTripId(bookingsDTO.getTripId());
//		bookings.setBookingDate(bookingsDTO.getBookingDate());
//		bookings.setSeatNumber(bookingsDTO.getSeatNumber());
//		bookings.setBookingStatus(bookingsDTO.getBookingStatus());
//		
////		when(passwordEncoderMock.encode(bookingsDTO.getPassword())).thenReturn("encodedPassword");
//		when(ibookingsRepositoryMock.save(any(Bookings.class))).thenReturn(bookings);
//		
//		Bookings createdBookings = bookingsService.createBookings(bookingsDTO);
////		verify(passwordEncoderMock)bookings.encode(bookingsDTO.getPassword());
//		verify(ibookingsRepositoryMock).save(any(Bookings.class));
//		
//		assertEquals(bookings.getBookingId(), createdBookings.getBookingId());
//		assertEquals(bookings.getTripId(), createdBookings.getTripId());
//		assertEquals(bookings.getBookingDate(), createdBookings.getBookingDate());
//		assertEquals(bookings.getSeatNumber(), createdBookings.getSeatNumber());
//		assertEquals(bookings.getBookingStatus(), createdBookings.getBookingStatus());
//  	}
//	
//	@Test
//	void testDeleteBookings() {
//		Bookings existingBooking = new Bookings();
//		existingBooking.setBookingId(existingBooking.getBookingId());
//		existingBooking.setTripId(existingBooking.getTripId());
//		existingBooking.setBookingDate(existingBooking.getBookingDate());
//		existingBooking.setSeatNumber(existingBooking.getSeatNumber());
//		existingBooking.setBookingStatus(existingBooking.getBookingStatus());
//	
//	    
//		when(ibookingsRepositoryMock.findById(existingBooking.getBookingId())).thenReturn(Optional.of(existingBooking));
//		bookingsService.deleteBookings(existingBooking.getBookingId());
//		
//		verify(ibookingsRepositoryMock).findById(existingBooking.getBookingId());
//		verify(ibookingsRepositoryMock).delete(existingBooking);
//		
//	    
//	}
//	
//	@Test
//	void testGetBookingsById () {
//		Bookings existingBooking = new Bookings();
//		existingBooking.setBookingId(existingBooking.getBookingId());
//		existingBooking.setTripId(existingBooking.getTripId());
//		existingBooking.setBookingDate(existingBooking.getBookingDate());
//		existingBooking.setSeatNumber(existingBooking.getSeatNumber());
//		existingBooking.setBookingStatus(existingBooking.getBookingStatus());
//	
//		when(ibookingsRepositoryMock.findById(existingBooking.getBookingId())).thenReturn(Optional.of(existingBooking));
//		
//		BookingsDTO bookingsDTO = bookingsService.getBookingsById(existingBooking.getBookingId());
//		verify(ibookingsRepositoryMock).findById(existingBooking.getBookingId());
//		
//		assertEquals(existingBooking.getBookingId(), bookingsDTO.getBookingId());
//		assertEquals(existingBooking.getTripId(), bookingsDTO.getTripId());
//		assertEquals(existingBooking.getBookingDate(), bookingsDTO.getBookingDate());
//		assertEquals(existingBooking.getSeatNumber(), bookingsDTO.getSeatNumber());
//		assertEquals(existingBooking.getBookingStatus(), bookingsDTO.getBookingStatus());
//
//    }
}
