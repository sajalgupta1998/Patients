package com.patient.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.patient.exception.AgeRestrictionError;
import com.patient.exception.DataIsEmpty;
import com.patient.exception.DiseaseNotFoundException;
import com.patient.patients.PatientData;
import com.patient.repository.Repository;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;
	
	public List<PatientData> getAllPatients() {
		if(repo.findAll().isEmpty()) {
		throw new DataIsEmpty("Data is Empty");
	}
		return repo.findAll();
	}

	public Optional<PatientData> getMaxagePatient() {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		return l.stream().max(Comparator.comparingInt(value->value.getAge()));
		}
	}
	
	public List<PatientData> getStartingCharacterAndDoctor(String character,String doctorName) {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		List<PatientData> d=l.stream().filter(val->val.getName().startsWith(character)).filter(val->val.getDoctor().equalsIgnoreCase(doctorName)).collect(Collectors.toList());
		//!d.stream().findAny().get().getDoctor().startsWith(s2)
//		if(l.stream().filter(val->val.getName().startsWith(s2))==null) {
//			throw new DataIsEmpty("hello");
//		}
		return d;
		}
	}
	
	public List<PatientData> getseniorCitizenWithDisease(String str) {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		List<PatientData> l2=l.stream().filter(dat->dat.getAge()>=60).filter(val->val.getDisease().equalsIgnoreCase(str)).collect(Collectors.toList());
		 return l2;
		}
	}

	public String addPatientWithDisease(PatientData data)   {
		List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
     	
		if(l.contains(data.getDisease())) {
			repo.save(data);
		}else {
			 throw new DiseaseNotFoundException("wrong disease");
		}
		return "Patients Details Added Successfully";
	}
		
		
public String addPatientWithAgeRestriction(PatientData data) {
	List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
	
	if(!l.contains(data.getDisease()) ) {
		 throw new AgeRestrictionError(" Please enter correct disease");
		
	}else if(!(data.getAge()>18)) {
		throw new AgeRestrictionError("Age Shoud be greater than 18 ");
		
		}
	else {
		repo.save(data);
		 
	}
	return "Patients Details Added Successfully";
}

public String deleteAllWithDiseaseName(String s) {
	List<PatientData> l=repo.findAll();
	if(repo.findAll().isEmpty()) {
		throw new DataIsEmpty("Data is Empty already");
	}else {
		
		List<PatientData> d=l.stream().filter(val->val.getDisease().equalsIgnoreCase(s)).collect(Collectors.toList());
		if(d.isEmpty()) {
			throw new DataIsEmpty("Disease name not present in this list");
		}else {
			repo.deleteAll(d);
			return "successfully Deleted";
		}
	}
}

public String deleteAll(){
	if(repo.findAll().isEmpty()) {
		throw new DataIsEmpty("Data is Empty already");
	}else {
	repo.deleteAll();
	return "Details deleted successfully";
	}
}

public String updatePatientName(PatientData data,String str) {
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
	 return "patient name is update";
}

public String UpdateAllSimilarDoctor(PatientData data, String str) {
	List<PatientData> p= repo.findAll();
	
//		if(p.stream().anyMatch(val->!val.getDoctor().equalsIgnoreCase(str))) {
//			throw new DataIsEmpty("Doctor name is not present");
//		}else {
	List<PatientData> d= p.stream().map(val->{
		 if(val.getDoctor().equalsIgnoreCase(str)) {
			 val.setDoctor(data.getDoctor());
		 }
		 return val;
	 }).collect(Collectors.toList());
	 repo.saveAll(d);
	return "List is updated";
	//}
}

public String UpdateAllSimilarDisease(PatientData data, String str) {
	List<String> l= Arrays.asList("diabetic","high cholestrol","dengue","covid","anthrax","cholera","brain injury","fever");
	List<PatientData> list= repo.findAll();
	List<PatientData> filterList= list.stream().filter(val->val.getDisease().equalsIgnoreCase(str)).collect(Collectors.toList());
     for(PatientData pd:filterList) {
    	 pd.setDisease(data.getDisease());
    	 if(l.contains(pd.getDisease())) {
    		 repo.save(pd);
    	 }else {
    		 throw new DiseaseNotFoundException("please enter correct disease");
    	 }
     }	
	return "List is updated";
}

}
