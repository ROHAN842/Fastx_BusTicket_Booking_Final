package com.hexaware.fastXBus.dto;

public class BusOperatorsDTO {
	private Long operatorId;
	private String operatorName;
	private String password;
	private String contactPhone;

	public BusOperatorsDTO() {
		}

	public BusOperatorsDTO(Long operatorId, String operatorName, String password, String contactPhone) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.password = password;
		this.contactPhone = contactPhone;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	


	
	
}