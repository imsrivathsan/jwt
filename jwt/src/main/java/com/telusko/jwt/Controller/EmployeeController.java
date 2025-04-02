package com.telusko.jwt.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.jwt.model.Employee;
import com.telusko.jwt.service.EmployeeService;
import com.telusko.jwt.util.ServiceException;

@RestController
@RequestMapping("/backendapp/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	public EmployeeController() {

	}

	@GetMapping(value = "/get/all")
	public ResponseEntity<List<Employee>> get() {

		List<Employee> employee = service.get();

		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@GetMapping(value = "/get/employee/by/id/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Integer id) {
		try {

			Employee employee = service.getById(id);

			return ResponseEntity.status(HttpStatus.OK).body(employee);

		} catch (ServiceException exception) {
			return ResponseEntity.status(exception.getStatus()).body(null);
		}

	}

	@PostMapping(value = "/add/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody Employee employee) {
		try {

			Employee newEmployee = service.add(employee);

			return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);

		} catch (ServiceException exception) {

			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}
	
	

	@PutMapping(value="/update/employee",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Employee employee) {
		try {

			Employee updatedEmployee = service.update(employee);

			return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);

		} catch (ServiceException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
		
	}
	
	

	@DeleteMapping(value = "/delete/by/id/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
		try {

			service.deleteById(id);

			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (ServiceException exception) {
			return ResponseEntity.status(exception.getStatus()).body(false);
		}
	}

}
