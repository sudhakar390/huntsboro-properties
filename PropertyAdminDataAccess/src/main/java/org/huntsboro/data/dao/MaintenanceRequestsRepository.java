package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.MaintenanceRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRequestsRepository extends JpaRepository<MaintenanceRequests, Serializable>{
	
	

}
