package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dao.PatientData;
import com.patient.service.ServiceLayer;


@RestController
public class Controller {

	@Autowired(required = true)
	ServiceLayer servicelayer;
	
	@GetMapping("/showAllPatients")
	public List<PatientData> showPatient() {
		return servicelayer.showAll();
	}
	
	@GetMapping("showByDisease/{diseaseName}")
	public List<PatientData> 
	
	@GetMapping("/greaterThan60")
	public List<PatientData> getAllPatient(@RequestBody PatientData data){
		return servicelayer.getPatient(data);
	}
	
	@PostMapping("/addNewPatients")
	public void adddata(@RequestBody List<PatientData> data) throws Exception {
		 servicelayer.add(data);
	}
	
//	@PostMapping("adddoctor")
//	public PatientData addda(@RequestBody PatientData data) {
//		
//		return servicelayer.add(data);
//	}
}
