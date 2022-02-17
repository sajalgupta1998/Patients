package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<PatientData> showPatient() {
		return new ResponseEntity( servicelayer.showAll(),HttpStatus.OK);
	}
	
	@GetMapping("/showPatientWithMaxAge")
	public ResponseEntity<PatientData> showMaxagePatient() {
		return new ResponseEntity( servicelayer.showthatPatient(),HttpStatus.OK);
	}
	
	@GetMapping("/startWithAnyAlphabetWithDocter/{str}/{doctorname}")
	public ResponseEntity<PatientData> showdisease(@PathVariable("str") String s1,@PathVariable("doctorname") String s2){
		return new ResponseEntity(servicelayer.showByDisease(s1,s2),HttpStatus.OK);
	}
	
	@GetMapping("/seniorCitizenWithDisease/{DiseaseName}")
	public ResponseEntity<PatientData> getAllPatient(@PathVariable("DiseaseName") String str){
		return new ResponseEntity(servicelayer.getPatient(str),HttpStatus.OK);
	}
	
	@PostMapping("/addNewPatientWithDisease")
	public ResponseEntity adddata(@RequestBody PatientData data) {
		 return new ResponseEntity(servicelayer.add(data),HttpStatus.CREATED);
	}
	
	@PostMapping("/addNewPatientWithAgeRestriction")
	public ResponseEntity addData(@RequestBody PatientData data) {
		 return new ResponseEntity(servicelayer.adds(data),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAllWithDiseaseName/{diseaseName}")
	public ResponseEntity deleteData(@RequestBody PatientData data,@PathVariable("diseaseName") String s) {
		return new ResponseEntity(servicelayer.delete(data,s),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity deleteData() {
		return new ResponseEntity(servicelayer.deleteAll(),HttpStatus.ACCEPTED);
	}
	
    @PutMapping("updatePatientName/{name}")
    public ResponseEntity update(@RequestBody PatientData data,@PathVariable("name") String str) {
    	return new ResponseEntity(servicelayer.updatedata(data,str),HttpStatus.OK);
    }
    
    @PutMapping("UpdateAllSimilarDoctor/{name}")
    public ResponseEntity updateDoc(@RequestBody PatientData data,@PathVariable("name") String str) {
    	return new ResponseEntity(servicelayer.updatedoc(data,str),HttpStatus.OK);
    }
    
//    @PutMapping("UpdateDisease/{disease}")
//    public ResponseEntity updateAge(@RequestBody PatientData data,@PathVariable("disease") String str) {
//    	return new ResponseEntity(servicelayer.updateAge(data,str),HttpStatus.OK);
//    }
	
	
}
