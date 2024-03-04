package com.hexaware.fastXBus.entity;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/*
 * Author:Vishal Anand
 * Date: 20-11-23
 */

@Entity
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	@NotNull
	@NotBlank(message = "First name cannot be blank")
	@Size(min=2, max=30)
	private String firstName;
	@NotNull
	@Size(min=2, max=30)
	private String lastName;
	@NotNull
	@Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
	private String email;
	@NotBlank
//    @Size(min = 4, max = 10, message = "Password must be between 4 and 10 characters")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,10}$", 
//             message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and no whitespace")
    private String password;
	@Pattern(regexp = "^[0-9]{10}")
    private String phoneNumber;
    private final String getRole = "ROLE_ADMIN";
	
	public Admin() {
		super();
	}

	 public Admin(Long adminId,
			@NotNull @NotBlank(message = "First name cannot be blank") @Size(min = 2, max = 30) String firstName,
			@NotNull @Size(min = 2, max = 30) String lastName,
			@NotNull @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$") String email,
			@NotBlank @Size(min = 4, max = 10, message = "Password must be between 4 and 10 characters") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,10}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and no whitespace") String password,
			@Pattern(regexp = "^[0-9]{10}") String phoneNumber) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGetRole() {
		return getRole;
	}
	
}
//d is an auto generated value shld not entered by keybard
//implement the frontend validation in all forms
//change the menu login/logout based on user action
//implement the history of booking for the user
//implement the seat selection functionality for ticket booking
//admin should list all bus
//Email notification should be implemented
//Role based authentication need to implemented with JWT token