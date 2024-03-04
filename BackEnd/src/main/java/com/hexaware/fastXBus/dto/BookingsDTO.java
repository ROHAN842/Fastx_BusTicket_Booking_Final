package com.hexaware.fastXBus.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingsDTO {
	
	private Long bookingId;
    private LocalDate bookingDate;
	private String email;
	private int totalcustomer;
	private double amount;
	private String seatNo;
	
	public BookingsDTO() {
		super();
	}

	public BookingsDTO(Long bookingId, LocalDate bookingDate, String email, int totalcustomer, double amount,
			String seatNo) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.email = email;
		this.totalcustomer = totalcustomer;
		this.amount = amount;
		this.seatNo = seatNo;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTotalcustomer() {
		return totalcustomer;
	}

	public void setTotalcustomer(int totalcustomer) {
		this.totalcustomer = totalcustomer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	
	
	
}
