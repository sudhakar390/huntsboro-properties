package org.huntsboro.data.dao;

import java.io.Serializable;
import java.util.List;

import org.huntsboro.data.entity.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UnitsRepository extends JpaRepository<Units, Serializable>{
	
	List<Units> findByStatus(@Param("status") String status);
	
	List<Units> findByPropertyIdAndUnitNumberAndRoomsAndBathroomAndStatus(Integer propertyId, Integer unitId, int rooms, int baths, String status);
	
	List<Units> findByPropertyId(Integer id);
	
	List<Units> findByPropertyIdAndStatus(Integer id, String status);
	
	List<Units> findByPropertyIdAndUnitNumberAndStatus(Integer propertyId, Integer unitId,String status);
	
	List<Units> findByPropertyIdAndUnitNumberAndRoomsAndStatus(Integer propertyId, Integer unitId, int rooms, String status);
	List<Units> findByPropertyIdAndUnitNumberAndBathroomAndStatus(Integer propertyId, Integer unitId, int baths, String status);
	
}
