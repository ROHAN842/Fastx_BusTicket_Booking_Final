package com.hexaware.fastXBus.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.dto.BusesDTO;
import com.hexaware.fastXBus.entity.Buses;
@Repository
public interface IBusesRepository extends JpaRepository<Buses,Long >{

	 @Query("SELECT b FROM Buses b WHERE b.sourceCity = :source AND b.destinationCity = :destination AND b.date = :date")
	    List<Buses> findAllBySourceCityAndDestinationCityAndDate(
	        @Param("source") String source,
	        @Param("destination") String destination,
	        @Param("date") LocalDate date
	    );
	 @Query("SELECT b.buses FROM Bookings b WHERE b.bookingId = :bookingId")
	    Buses findBusDetailsByBookingId(@Param("bookingId") Long bookingId);
}
