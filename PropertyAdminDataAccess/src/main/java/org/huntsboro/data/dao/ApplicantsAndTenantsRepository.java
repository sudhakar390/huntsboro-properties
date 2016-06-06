package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.ApplicantsAndTenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicantsAndTenantsRepository extends JpaRepository<ApplicantsAndTenants, Serializable>{

	ApplicantsAndTenants findByEmail(@Param("email") String email); 
	
	ApplicantsAndTenants findByEmailAndPassword(@Param("email")String email, @Param("password") String password);
}
