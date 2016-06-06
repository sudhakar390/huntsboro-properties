package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesRepository extends JpaRepository<Properties, Serializable>{
	

}
