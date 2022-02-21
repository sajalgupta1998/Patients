package com.patient.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dao.PatientData;
import com.patient.service.ServiceLayer;


@RestController
public class Controller {

	@Autowired(required = true)
	ServiceLayer servicelayer;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/showAllPatients")
	public List<PatientData> showPatient() {
		return servicelayer.showAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/showPatientWithMaxAge")
	public Optional<PatientData> showMaxagePatient() {
		return servicelayer.showthatPatient();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/startWithAnyAlphabetWithDocter/{str}/{doctorname}")
	public List<PatientData> showdisease(@PathVariable("str") String s1,@PathVariable("doctorname") String s2){
		return servicelayer.showByDisease(s1,s2);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/seniorCitizenWithDisease/{DiseaseName}")
	public List<PatientData> getAllPatient(@PathVariable("DiseaseName") String str){
		return servicelayer.getPatient(str);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addNewPatientWithDisease")
	public String adddata(@Valid @RequestBody PatientData data) {
		 return servicelayer.add(data);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addNewPatientWithAgeRestriction")
	public String addData(@Valid @RequestBody PatientData data) {
		 return servicelayer.adds(data);
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/deleteAllWithDiseaseName/{diseaseName}")
	public String deleteData(@RequestBody PatientData data,@PathVariable("diseaseName") String s) {
		return servicelayer.delete(data,s);
	}

	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteData() {
		return servicelayer.deleteAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("updatePatientName/{name}")
    public String update(@Valid @RequestBody PatientData data,@PathVariable("name") String str) {
    	return servicelayer.updatedata(data,str);
    }
    
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("UpdateAllSimilarDoctor/{name}")
    public String updateDoc(@Valid @RequestBody PatientData data,@PathVariable("name") String str) {
    	return servicelayer.updatedoc(data,str);
    }
    
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("UpdateDiseaseAndPatientAge/{disease}")
    public String updateAge(@Valid @RequestBody PatientData data,@PathVariable("disease") String str) {
    	return servicelayer.updateDisease(data,str);
    }
	
}
