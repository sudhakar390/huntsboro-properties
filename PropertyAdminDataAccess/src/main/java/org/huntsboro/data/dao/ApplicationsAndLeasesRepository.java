package org.huntsboro.data.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.huntsboro.data.entity.ApplicationsAndLeases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationsAndLeasesRepository extends JpaRepository<ApplicationsAndLeases, Serializable>{
	
	List<ApplicationsAndLeases> findByPropertyAndUnit(Integer propertyId, Integer unitId);
	List<ApplicationsAndLeases> findByEndDateBefore(Date searchDate);
}
