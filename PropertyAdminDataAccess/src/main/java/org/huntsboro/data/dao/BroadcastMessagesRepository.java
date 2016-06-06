package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.BroadcastMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastMessagesRepository extends JpaRepository<BroadcastMessages, Serializable>{
	
	

}
