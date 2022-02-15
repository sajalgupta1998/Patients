package com.patient.service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dao.PatientData;
import com.patient.repository.Repository;
import com.sun.net.httpserver.HttpsConfigurator;

@Service
public class ServiceLayer {
	
	@Autowired
	Repository repo;
	
	public List<PatientData> showAll() {
		
		return repo.findAll();
	}
	
	

	public List<PatientData> getPatient(PatientData data) {
		List<PatientData> l=repo.findAll();
		List<PatientData> l2=l.stream().filter(dat->dat.getAge()>40).collect(Collectors.toList());
		 return l2;
	}

	public void add(List<PatientData> data) throws Exception {
		String str[]= {"Diabetic","High cholestrol","Dengue","Covid","Anthrax","Cholera","brain injury","Fever"};
//		List<String> l2=Arrays.asList(str);
		int count=0;
//		
//		List<PatientData> l=(List<PatientData>) data.stream().filter(val->val.equals(l2)).count());
		//str.stream().filter(s->s.startsWith("C")).forEachOrdered(val->System.out.println(val));
		for(int i=0;i<str.length;i++) {
		if(((PatientData) data).getDisease().equalsIgnoreCase(str[i])) {
			count ++;
		}
		}
		
		if(count>0) {
			System.out.println("data added successfully done ");
			repo.save(data);
		}else {
			 throw new Exception("please enter disease correctly");
			
		}
		
	}
	
public PatientData addp(PatientData data) {
		
		if(data.getDisease().equals("maleria")) {
			data.setDoctor(data.getDoctor());
		}
		return repo.save(data);
	}



public List<PatientData> showByDisease(PatientData data) {
	repo.fi
	return null;
}



}
