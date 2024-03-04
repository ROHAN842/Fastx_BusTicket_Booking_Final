package com.hexaware.fastXBus.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.fastXBus.dto.BusOperatorsDTO;
import com.hexaware.fastXBus.entity.BusOperators;

public interface IBusOperatorsService {

	public BusOperators createBusOperators(BusOperatorsDTO busoperatorsdto);
	public BusOperators updateBusOperators(BusOperatorsDTO busoperatorsdto,Long operatorId);
	public void deleteBusOperators(Long operatorId);
	public BusOperatorsDTO getBusOperatorsById(Long OperatorId);
	
	public List<BusOperators>getAllBusOperators();
	Optional<BusOperators> findByoperatorName(String operatorName);
}
