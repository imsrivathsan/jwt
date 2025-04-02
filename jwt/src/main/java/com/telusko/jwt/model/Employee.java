package com.telusko.jwt.model;
import jakarta.persistence.*;

@Entity
@Table(schema="public",name="employee")
public class Employee {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="emp_id")
	private String empId;
	
	@Column(name="designation")
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
	

	
	
	

	
	
	

}
