package com.hexaware.fastXBus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.Admin;


@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long > {

	Optional<Admin> findByfirstName(String firstName);
	 @Query("SELECT r.getRole FROM Admin r WHERE r.firstName = :adName")
	    public String getRoleByFirstName(@Param("adName") String firstName);
	 
		public boolean existsByEmail(String email);

	
}
