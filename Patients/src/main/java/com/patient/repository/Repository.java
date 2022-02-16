package com.patient.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.patient.dao.PatientData;

public interface Repository extends JpaRepository<PatientData, Integer>{

	
	// List<PatientData> deleteByName();
	
}
