package com.hexaware.fastXBus.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class BusesDTO {
	private Long busId;
	private String busNumber;
	private int capacity;
	private String sourceCity;
	private String destinationCity;
	private int distanceInKms;
	private int durationInHours;
	private String departureTime;
	private float fare;
	private LocalDate date;
	private int totalSeats;
	
	

	public BusesDTO() {
	}



	public BusesDTO(Long busId, String busNumber, int capacity, String sourceCity, String destinationCity,
			int distanceInKms, int durationInHours, String departureTime, float fare, LocalDate date,int totalSeats) {
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



	public int getTotalSeats() {
		return totalSeats;
	}



	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	





}
