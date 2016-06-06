package org.huntsboro.data.dao;

import java.io.Serializable;
import java.util.List;

import org.huntsboro.data.entity.ResidenceAndRentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceAndRentalHistoryRepository extends JpaRepository<ResidenceAndRentalHistory, Serializable>{
	
	List<ResidenceAndRentalHistory> findByTenantId(Integer tenantId);

}
