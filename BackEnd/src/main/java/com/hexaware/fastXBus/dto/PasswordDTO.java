package com.hexaware.fastXBus.dto;

public class PasswordDTO {
	
	private String email;
	private String newPassword;
	
	public PasswordDTO() {
		super();
	}


	public PasswordDTO( String email,String newPassword) {
		super();
		
		this.email = email;
		this.newPassword= newPassword;
	}


	


	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
