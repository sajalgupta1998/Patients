package com.patient.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.patient.dao.PatientData;
import com.patient.exception.DataIsEmpty;
import com.patient.service.ServiceLayer;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
    @MockBean
	ServiceLayer service;
    
    //controller class test
	@Test
	void showPatientSuccessTest() throws Exception {
		List<PatientData> l=new ArrayList<>();
		PatientData p=new PatientData(1,"Anna Varela","Diabetic",40,"Liam Miller");
		l.add(p);
		//when(service.showAll()).thenReturn(l);
		mockMvc.perform(get("/showAllPatients"))
		.andExpect(status().isOk());
		verify(service,times(1)).showAll();

	}
	@Test
	void showPatientTestError() throws Exception {
//		List<PatientData> l=new ArrayList<>();
//		PatientData p=new PatientData(1,"sajal","saddsad",66,"sadsadds");
//		l.add(p);
		when(service.showAll().isEmpty()).thenThrow(DataIsEmpty.class);
		mockMvc.perform(get("/showAllPatients"))
		.andExpect(status().is4xxClientError())
		.andExpect(result -> 
		assertTrue(result.getResolvedException() instanceof DataIsEmpty));
//		.andExpect(result -> 
//		assertEquals("Data is Empty", result.getResolvedException().getMessage()));
		verify(service,times(1)).showAll();

	}
	
	//not working
	@Test
	void AddPatientsuccessTest() throws Exception {
		List<PatientData> l=new ArrayList<>();
		PatientData p=new PatientData(1,"Anna Varela","Diabetic",40,"Liam Miller");
		l.add(p);
//		when(service.add(p)).thenReturn(l);
		mockMvc.perform(post("/addNewPatientWithDisease"))
		.andExpect(status().isCreated());
		verify(service,times(1)).add(p);

	}

}
