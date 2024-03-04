package com.hexaware.fastXBus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.entity.BusOperators;
@Repository
public interface IBusOperatorsRepository extends JpaRepository<BusOperators,Long >{
	
	Optional<BusOperators> findByoperatorName(String operatorName);
	 @Query("SELECT o.role FROM BusOperators o WHERE o.operatorName = :Name")
	    public String getRoleByOperatorName(@Param("Name") String operatorName);
	}
