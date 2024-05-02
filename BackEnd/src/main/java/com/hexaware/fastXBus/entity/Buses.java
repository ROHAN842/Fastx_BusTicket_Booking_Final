package com.hexaware.fastXBus.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;




@Entity
public class Buses implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_id_sequence")
	@SequenceGenerator(name = "bus_id_sequence", sequenceName = "bus_id_seq", initialValue = 7000, allocationSize = 1)
	private Long busId;
	@NotBlank
	private String busNumber;
	private int capacity;
	@NotBlank
	private String sourceCity;
	@NotBlank
	private String destinationCity;
	
	private int distanceInKms;
	
	private int durationInHours;
	@NotBlank
	private String departureTime;
	@Positive
	private float fare;
	private LocalDate date;
	
	private int totalSeats;
	@ManyToOne
	@JoinColumn(name="operatorId")
	private BusOperators busOperators;
	@OneToMany(mappedBy ="buses")
	private List<Bookings> booking=new ArrayList();
	public Buses() {
		super();
	}
	public Buses(Long busId, @NotBlank String busNumber, int capacity, @NotBlank String sourceCity,
			@NotBlank String destinationCity, int distanceInKms, int durationInHours, @NotBlank String departureTime,
			@Positive float fare, LocalDate date,int totalSeats, BusOperators busOperators, List<Bookings> booking) {
		super();
		this.busId = busId;
		this.busNumber = busNumber;
		this.capacity = capacity;
		this.sourceCity = sourceCity;
		this.destinationCity = destinationCity;
		this.distanceInKms = distanceInKms;
		this.durationInHours = durationInHours;
		this.departureTime = departureTime;
		this.fare = fare;
		this.date = date;
		this.busOperators = busOperators;
		this.booking = booking;
	}
	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public int getDistanceInKms() {
		return distanceInKms;
	}
	public void setDistanceInKms(int distanceInKms) {
		this.distanceInKms = distanceInKms;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public float getFare() {
		return fare;
	}
	public void setFare(float fare) {
		this.fare = fare;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BusOperators getBusOperators() {
		return busOperators;
	}
	public void setBusOperators(BusOperators busOperators) {
		this.busOperators = busOperators;
	}
	public List<Bookings> getBooking() {
		return booking;
	}
	public void setBooking(List<Bookings> booking) {
		this.booking = booking;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	
	
	
}
