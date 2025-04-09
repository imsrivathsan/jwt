package com.telusko.jwt.model;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;







@Entity
@Table(name="employee")
public class Employee {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="emp_id")
	@NotBlank(message = "empId is mandatory")
	private String empId;
	
	@Column(name="designation")
	@NotBlank(message = "designation is mandatory")
	private  String designation;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empId=" + empId + ", designation=" + designation + "]";
	}

	public Employee(int id, String empId, String designation) {
		super();
		this.id = id;
		this.empId = empId;
		this.designation = designation;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException exception) {

			System.out.println("Exception while converting employee object into json String");
		}

		return "{}";
	}
	
	
	

	
	
	

}
