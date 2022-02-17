package com.patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/showPatientWithMaxAge")
	public Optional<PatientData> showMaxagePatient() {
		return servicelayer.showthatPatient();
	}
	
	@GetMapping("/startWithAnyAlphabetWithDocter/{str}/{doctorname}")
	public List<PatientData> showdisease(@PathVariable("str") String s1,@PathVariable("doctorname") String s2){
		return servicelayer.showByDisease(s1,s2);
	}
	
	@GetMapping("/seniorCitizenWithDisease/{DiseaseName}")
	public List<PatientData> getAllPatient(@PathVariable("DiseaseName") String str){
		return servicelayer.getPatient(str);
	}
	
	@PostMapping("/addNewPatientWithDisease")
	public String adddata(@RequestBody PatientData data) {
		 return servicelayer.add(data);
	}
	
	@PostMapping("/addNewPatientWithAgeRestriction/{age}")
	public String addData(@RequestBody PatientData data,@PathVariable("age") int id) {
		 return servicelayer.adds(data,id);
	}
	
	@DeleteMapping("/deleteAllWithDiseaseName/{diseaseName}")
	public String deleteData(@RequestBody PatientData data,@PathVariable("diseaseName") String s) {
		return servicelayer.delete(data,s);
	}

	@DeleteMapping("/deleteAll")
	public String deleteData() {
		return servicelayer.deleteAll();
	}
	
	
	
}
