package com.hexaware.fastXBus.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.Bookings;

@Repository
public interface IBookingsRepository extends JpaRepository<Bookings, Long> {
    
    @Query("SELECT b.seatNo FROM Bookings b WHERE b.buses.date = :tDate AND b.buses.busId = :busId")
    public List<String> fetchBookedSeats(@Param("tDate") LocalDate date, @Param("busId") Long busId);
    @Query("SELECT b FROM Bookings b LEFT JOIN FETCH b.buses WHERE b.bookingId = :bookingId")
    Bookings findBookingWithBusesById(@Param("bookingId") Long bookingId);
    @Query("SELECT b FROM Bookings b WHERE b.userCustomers.userId = :userId")
    public List<Bookings> getAllBookingsByUserId(@Param("userId")Long userId);
	
    

}
