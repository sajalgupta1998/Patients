package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patient.dao.PatientData;

public interface Repository extends JpaRepository<PatientData, Integer>{
	
}
