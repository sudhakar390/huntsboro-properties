package org.huntsboro.data.dao;

import java.io.Serializable;

import org.huntsboro.data.entity.EmploymentIncomeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentIncomeHistoryRepository extends JpaRepository<EmploymentIncomeHistory, Serializable>{

}
