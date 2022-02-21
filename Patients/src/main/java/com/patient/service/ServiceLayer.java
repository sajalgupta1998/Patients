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
import com.patient.exception.DataIsEmpty;
import com.patient.exception.DiseaseNotFoundException;
import com.patient.repository.Repository;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;
	
	public List<PatientData> showAll() {
		if(repo.findAll().isEmpty()) {
		throw new DataIsEmpty("Data is Empty");
	}
		return repo.findAll();
	}
	
	public Optional<PatientData> showthatPatient() {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		return l.stream().max(Comparator.comparingInt(value->value.getName().length()));
		}
	}
	public List<PatientData> showByDisease(String s1,String s2) {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		return l.stream().filter(val->val.getName().startsWith(s1)).filter(val->val.getDoctor().equalsIgnoreCase(s2)).collect(Collectors.toList());
		}
	}
	
	public List<PatientData> getPatient(String str) {
		if(repo.findAll().isEmpty()) {
			throw new DataIsEmpty("Data is Empty");
		}else {
		List<PatientData> l=repo.findAll();
		List<PatientData> l2=l.stream().filter(dat->dat.getAge()>40).filter(val->val.getDisease().equalsIgnoreCase(str)).collect(Collectors.toList());
		 return l2;
		}
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
	
	if(!l.contains(data.getDisease()) ) {
		 throw new AgeRestrictionError(" Please enter correct disease");
		
	}else if(!(data.getAge()>18)) {
		throw new AgeRestrictionError("Age Restrictions ");
		
		}
	else {
		repo.save(data);
		 
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
	 return "patient name is update";
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
	return "List is updated";
}

public String updateDisease(PatientData data, String str) {
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
