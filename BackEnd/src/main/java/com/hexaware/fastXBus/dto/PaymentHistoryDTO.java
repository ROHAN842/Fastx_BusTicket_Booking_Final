package com.hexaware.fastXBus.dto;

import java.time.LocalDate;

public class PaymentHistoryDTO {
	private Long paymentId;
	
	private float amountPaid;
	private LocalDate paymentDate;
	

	public PaymentHistoryDTO() {
	
	}

	public PaymentHistoryDTO(Long paymentId,  float amountPaid, LocalDate paymentDate) {
		super();
		this.paymentId = paymentId;
		
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
	
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	
	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	

	@Override
	public String toString() {
		return "PaymentHistory [paymentId=" + paymentId + ", bookingId=" + ", amountPaid=" + amountPaid
				+ ", paymentDate=" + paymentDate + ", userId=" +  "]";
	}

	
	

}
