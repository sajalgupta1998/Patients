package com.patient.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dao.PatientData;
import com.patient.exception.AgeRestrictionError;
import com.patient.exception.DiseaseNotFoundException;
import com.patient.repository.Repository;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;
	
	public List<PatientData> showAll() {
//		if(repo.findAll().isEmpty()) {
//		throw new DataIsEmpty("Data is Empty");
//	}
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
		
		
public String adds(PatientData data) {
	List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
	
	if(l.contains(data.getDisease()) && data.getAge()>18) {
		repo.save(data);
	}else {
		 throw new AgeRestrictionError("Age Restrictions and Please enter correct disease");
		 
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
	return "Details deleted successfully";
}

public String updatedata(PatientData data,String str) {
	  List<PatientData> p= repo.findAll();
//	  for(PatientData pt:p) {
//		  if(pt.getName().equalsIgnoreCase(str)) {
//			 PatientData aa= repo.findById(pt.getId()).get();
//			 aa.setName(data.getName());
//			 repo.save(aa);
//		  }
//	  }
	 List<PatientData> d= p.stream().map(val->{
		 if(val.getName().equalsIgnoreCase(str)) {
			 val.setName(data.getName());
		 }
		 return val;
	 }).collect(Collectors.toList());
	 repo.saveAll(d);
	 return "Update patient name";
}

public String updatedoc(PatientData data, String str) {
	List<PatientData> p= repo.findAll();
	List<PatientData> d= p.stream().map(val->{
		 if(val.getDoctor().equalsIgnoreCase(str)) {
			 val.setDoctor(data.getDoctor());
		 }
		 return val;
	 }).collect(Collectors.toList());
	 repo.saveAll(d);
	return "Updated";
}

//public String updateAge(PatientData data, String str) {
//	List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
//	List<PatientData> p= repo.findAll();
//	List<PatientData> d= p.stream().map(val->{
//		if(l.contains(val.getDisease()) {
//		 if(val.getDisease().equalsIgnoreCase(str)) {
//			 val.setDoctor(data.getDoctor());
//		 }
//		}
//		 return val;
//	 }).collect(Collectors.toList());
//	 repo.saveAll(d);
//	
//	return null;
//}








}
