package com.hexaware.fastXBus.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookingsDTO> createBookings(
            @RequestBody BookingsDTO bookingdto,
            @PathVariable Long userId,
            @PathVariable Long busId
    ) {
        BookingsDTO createdBooking = booking.createBookings(bookingdto, userId, busId);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookingId}")
    @PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
    public ResponseEntity<Bookings> updatebookings(@RequestBody BookingsDTO bookingsdto, @PathVariable Long bookingId) {
        logger.info("Booking updated");
        Bookings updatedBooking = booking.updateBookings(bookingsdto, bookingId);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/delete/{bookingId}")
    @PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
    public ResponseEntity<Void> deleteBookings(@PathVariable Long bookingId) {
        logger.info("Booking deleted");
        booking.deleteBookings(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByUserId/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<List<BookingsDTO>> getAllBookingsByUserId(@PathVariable Long userId) {
        List<BookingsDTO> bookings = booking.getAllBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/getById/{bookingId}")
    @PreAuthorize("hasAnyAuthority('ROLE_BUSOPERATOR','ROLE_USER')")
    public ResponseEntity<BookingsDTO> getById(@PathVariable Long bookingId) throws BookingNotFoundException {
        if (bookingId == 0) {
            throw new BookingNotFoundException(HttpStatus.BAD_REQUEST, "booking not found" + bookingId);
        }
        BookingsDTO bookingDTO = booking.getBookingsById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<BookingsDTO>> getAll() {
        logger.info("All Booking ");
        List<BookingsDTO> allBookings = booking.getAllBookings();
        return ResponseEntity.ok(allBookings);
    }

    @GetMapping("/fetchbookedseats/{date}/{busId}")
    public ResponseEntity<List<String>> fetchBookedSeats(@PathVariable String date, @PathVariable long busId) {
        LocalDate tdate = LocalDate.parse(date.trim());
        List<String> bookedSeats = booking.fetchBookedSeats(tdate, busId);
        return ResponseEntity.ok(bookedSeats);
    }

    @GetMapping("/sendemailonbooking/{bookingId}")
    public ResponseEntity<Void> confirmBooking(@PathVariable Long bookingId) {
        booking.sendBookingConfirmationEmail(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
} 
