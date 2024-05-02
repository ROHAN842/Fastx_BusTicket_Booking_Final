package com.hexaware.fastXBus.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;




@Entity
public class Bookings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
    private LocalDate bookingDate;
    @NotNull
	@Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
	private String email;
    @Min(value = 1, message = "Total customers must be at least 1")
	private int totalcustomer;
    @Positive(message = "Amount must be a positive number")
	private double amount;
    @NotBlank
	private String seatNo;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserCustomers userCustomers;
	@ManyToOne
	@JoinColumn(name="busId")
	private Buses buses;
	
	
	

	
	
	public Bookings() {
		super();
	}


	public Bookings(Long bookingId, LocalDate bookingDate, String email, int totalcustomer, double amount, String seatNo,
			UserCustomers userCustomers, Buses buses) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.email = email;
		this.totalcustomer = totalcustomer;
		this.amount = amount;
		this.seatNo = seatNo;
		this.userCustomers = userCustomers;
		this.buses = buses;
		
		
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


	public UserCustomers getUserCustomers() {
		return userCustomers;
	}


	public void setUserCustomers(UserCustomers userCustomers) {
		this.userCustomers = userCustomers;
	}


	public Buses getBuses() {
		return buses;
	}


	public void setBuses(Buses buses) {
		this.buses = buses;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}