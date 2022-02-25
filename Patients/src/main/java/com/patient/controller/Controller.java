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

import com.patient.patients.PatientData;
import com.patient.service.ServiceLayer;


@RestController
public class Controller {

	@Autowired(required = true)
	ServiceLayer servicelayer;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/showAllPatients")
	public List<PatientData> showAllPatients() {
		return servicelayer.getAllPatients();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/showPatientWithMaxAge")
	public Optional<PatientData> showMaxagePatient() {
		return servicelayer.getMaxagePatient();
	}
	//show patient name start with any character with doctor name/startingCharacter/doctorName
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/startWithAnyAlphabetWithDocter/{startingCharacter}/{doctorname}")
	public List<PatientData> showStartingCharacterAndDocter(@PathVariable("startingCharacter") String character,@PathVariable("doctorname") String doctorname){
		return servicelayer.getStartingCharacterAndDoctor(character,doctorname);
	}
	//seniorCitizen/diseaseName
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/seniorCitizenWithDisease/{DiseaseName}")
	public List<PatientData> seniorCitizenWithDisease(@PathVariable("DiseaseName") String str){
		return servicelayer.getseniorCitizenWithDisease(str);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addNewPatientWithDisease")
	public String addNewPatientWithDisease(@Valid @RequestBody PatientData data) {
		 return servicelayer.addPatientWithDisease(data);
	}
	//correct age with above 18 year 
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addNewPatientWithAgeRestriction")
	public String addData(@Valid @RequestBody PatientData data) {
		 return servicelayer.addPatientWithAgeRestriction(data);
	}
	
	//enter the endPoint and disease name
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/deleteAllWithDiseaseName/{diseaseName}")
	public String deleteData(@PathVariable("diseaseName") String s) {
		return servicelayer.deleteAllWithDiseaseName(s);
	}

	@DeleteMapping("/deleteAll")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String deleteAll() {
		return servicelayer.deleteAll();
	}
	//only update patient name
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("updatePatientName/{patientName}")
    public String update(@Valid @RequestBody PatientData data,@PathVariable("patientName") String str) {
    	return servicelayer.updatePatientName(data,str);
    }
    //update all similar doctor name
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("UpdateAllSimilarDoctor/{docterName}")
    public String updateDoc(@Valid @RequestBody PatientData data,@PathVariable("patientName") String str) {
    	return servicelayer.UpdateAllSimilarDoctor(data,str);
    }
    //updating all the similar disease name
	@ResponseStatus(HttpStatus.OK)
    @PutMapping("UpdateAllSimilarDisease/{disease}")
    public String updateAge(@Valid @RequestBody PatientData data,@PathVariable("disease") String str) {
    	return servicelayer.UpdateAllSimilarDisease(data,str);
    }
	
}
