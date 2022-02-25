package com.patient.patients;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="Patients")
public class PatientData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Patient name should not be empty")
	@Size(min = 3,max = 20,message = "name size should be 3-20 charachter")
	private String name;
	
	@NotEmpty(message = "Disease name should not be empty")
	private String disease;
	
	@Positive(message = "Age should not be negetive or zero or Empty")
	private int age;
	
	public PatientData(int id, String name,String disease,int age,String doctor) {
		super();
		this.id = id;
		this.name = name;
		this.disease = disease;
		this.age = age;
		this.doctor = doctor;
	}
	public PatientData() {
		super();
		
	}
	@NotEmpty(message = "Doctor name should not be empty")
	private String doctor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "PatientData [id=" + id + ", name=" + name + ", disease=" + disease + ", age=" + age + ", doctor="
				+ doctor + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
}
