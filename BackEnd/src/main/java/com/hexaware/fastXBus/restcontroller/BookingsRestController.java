package com.hexaware.fastXBus.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.BookingsDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.exceptions.BookingNotFoundException;
import com.hexaware.fastXBus.service.IBookingsService;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsRestController {
	@Autowired
	private IBookingsService booking;
	private static final Logger logger = LoggerFactory.getLogger(BookingsRestController.class);
	@PostMapping("/add/{busId}/{userId}")
	public BookingsDTO createBookings(
	        @RequestBody BookingsDTO bookingdto,
	        @PathVariable Long userId,
	        @PathVariable Long busId
	) {
	    return booking.createBookings(bookingdto, userId, busId);
	}
	@PutMapping("/update/{bookingId}")
	@PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
	public Bookings updatebookings(@RequestBody BookingsDTO bookingsdto,@PathVariable Long bookingId) {
		logger.info("Booking updated");
		return  booking.updateBookings(bookingsdto,bookingId);
	}
	@DeleteMapping("/delete/{bookingId}")
	@PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
	public void deleteBookings(@PathVariable Long bookingId)
	{
		logger.info("Booking deleted");
		 booking.deleteBookings(bookingId);
	
	}
	@GetMapping("/getByUserId/{userId}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public List<BookingsDTO> getAllBookingsByUserId(@PathVariable Long userId) {
	    return booking.getAllBookingsByUserId(userId);
	}
	@GetMapping("/getById/{bookingId}")
	@PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
	public BookingsDTO getById(@PathVariable Long bookingId)throws BookingNotFoundException{
		
		
		if(bookingId==0) {
			throw new BookingNotFoundException(HttpStatus.BAD_REQUEST,"booking not found"+bookingId);
		}
		return  booking.getBookingsById(bookingId);	
		
	}
	@GetMapping("/getall")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<BookingsDTO> getAll(){
		logger.info("All Booking ");
		return  booking.getAllBookings();	
		
	}
	
	@GetMapping("/fetchbookedseats/{date}/{busId}")

	public List<String> fetchBookedSeats(@PathVariable String date, @PathVariable long busId) {
    
        LocalDate tdate = LocalDate.parse(date.trim());
		return booking.fetchBookedSeats(tdate, busId);
	}
	@GetMapping("/sendemailonbooking/{bookingId}")
	
	public void confirmBooking(@PathVariable Long bookingId) {
        
        booking.sendBookingConfirmationEmail(bookingId);
    }
	
	
}
