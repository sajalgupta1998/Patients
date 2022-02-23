package com.patient.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.patient.dao.PatientData;
import com.patient.exception.DataIsEmpty;
import com.patient.repository.Repository;

@SpringBootTest
class PatientServiceTest {

	 @Mock 
		Repository repo;
		
	    @InjectMocks
		ServiceLayer service;
		
		@BeforeEach
	    public void setUp(){
			service= new ServiceLayer(this.repo);
	    }

		
		@Test
		void showAllSuccessTest() {
	        when(repo.findAll()).thenReturn(Arrays.asList(new PatientData(1,"Anna Varela","diabetic",40,"Liam Miller")));
			List<PatientData> empList = service.showAll();
			assertFalse(empList.isEmpty());
			assertEquals(1, empList.size());
			assertEquals(empList.get(0).getId(), 1);
	    	verify(repo,times(2)).findAll();
		}
		
		@Test
		void showAllTestError() {
	        when(repo.findAll()).thenReturn(Arrays.asList(new PatientData()));
			List<PatientData> empList = service.showAll();
			when(repo.findAll().isEmpty()).thenThrow(DataIsEmpty.class);
			verify(repo,times(2)).findAll();
		}

}
