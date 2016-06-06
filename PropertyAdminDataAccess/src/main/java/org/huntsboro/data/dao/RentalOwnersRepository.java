package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.RentalOwners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalOwnersRepository extends JpaRepository<RentalOwners, Serializable>{
	
	

}
