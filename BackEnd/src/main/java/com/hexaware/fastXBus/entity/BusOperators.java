package com.hexaware.fastXBus.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Author:Vishal Anand
 * Date: 20-11-23
 */




@Entity
public class BusOperators implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operator_sequence")
    @SequenceGenerator(name = "operator_sequence", sequenceName = "operator_sequence", initialValue = 1001, allocationSize = 1)
	private Long operatorId;
	@NotBlank
    @Size(min = 2, max = 20)
	private String operatorName;
	@NotBlank
    private String password;
	@Pattern(regexp = "^[0-9]{10}")
	private String contactPhone;
	private final String role = "ROLE_BUSOPERATOR";
	@OneToMany(mappedBy = "busOperators")
	private List<Buses>buses=new ArrayList<>();
	
	
	public BusOperators() {
		super();
	}


	public BusOperators(Long operatorId, @NotBlank @Size(min = 2, max = 20) String operatorName,
			@NotNull String password, @Pattern(regexp = "^[0-9]{10}") String contactPhone, List<Buses> buses) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.password = password;
		this.contactPhone = contactPhone;
		this.buses = buses;
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


	public List<Buses> getBuses() {
		return buses;
	}


	public void setBuses(List<Buses> buses) {
		this.buses = buses;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getRole() {
		return role;
	}
	


	
	
	
}