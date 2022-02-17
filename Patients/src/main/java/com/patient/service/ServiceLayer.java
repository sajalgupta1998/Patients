package com.patient.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dao.PatientData;
import com.patient.exception.DiseaseNotFoundException;
import com.patient.repository.Repository;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;
	
	public List<PatientData> showAll() {
		return repo.findAll();
	}
	
	public List<PatientData> showByDisease(String s1,String s2) {
		List<PatientData> l=repo.findAll();
		return l.stream().filter(val->val.getName().startsWith(s1)).filter(val->val.getDoctor().equalsIgnoreCase(s2)).collect(Collectors.toList());
		 
	}
	
	public Optional<PatientData> showthatPatient() {
		List<PatientData> l=repo.findAll();
		
		return l.stream().max(Comparator.comparingInt(value->value.getName().length()));
		 
	}

	
	public List<PatientData> getPatient(String str) {
		List<PatientData> l=repo.findAll();
		List<PatientData> l2=l.stream().filter(dat->dat.getAge()>40).filter(val->val.getDisease().equalsIgnoreCase(str)).collect(Collectors.toList());
		 return l2;
	}

	public String add(PatientData data)   {
		List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
     	
		if(l.contains(data.getDisease())) {
			repo.save(data);
		}else {
			 throw new DiseaseNotFoundException("wrong disease");
		}
		return "Patients Details Added Successfully";
	}
		
		
public String adds(PatientData data,int id) {
	List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
	//l.stream().filter(data->data)
	if(l.contains(data.getDisease()) && data.getAge()>id) {
		repo.save(data);
	}else {
		return "Please Verify the Age";
	}
	return "Patients Details Added Successfully";
}


public String delete(PatientData data,String s) {
	List<PatientData> l=repo.findAll();
	for(PatientData d:l) {
		if(d.getDisease().equalsIgnoreCase(s)) {
			repo.delete(d);
		}
	}

	return "successfully Deleted";
}

public String deleteAll() {
	repo.deleteAll();
	return "";
}








}
