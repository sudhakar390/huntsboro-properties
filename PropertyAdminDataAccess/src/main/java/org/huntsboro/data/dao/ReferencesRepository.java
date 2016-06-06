package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.References;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferencesRepository extends JpaRepository<References, Serializable>{
	
	

}
