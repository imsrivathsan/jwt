package com.telusko.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.telusko.jwt.model.Employee;
import com.telusko.jwt.repository.EmployeeRepository;
import com.telusko.jwt.util.ServiceException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeeService() {

	}

	public List<Employee> get() {

		List<Employee> lstOfEmployee = repository.findAll();
		
		System.out.println("getting all the employee in the objects========================>>>>>>>>>>" + lstOfEmployee);

		return lstOfEmployee;
	}

	public Employee getById(Integer id) throws ServiceException {
		if (id > 0) {
			Optional<Employee> response = repository.findById(id);

			if (response.isPresent()) {
				return response.get();
			} else {
				throw new ServiceException(HttpStatus.NOT_FOUND, "Employee with this Id: " + id + "not found");
			}
		} else {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid country Id: " + id);
		}
	}

	public Employee add(Employee employee) throws ServiceException {

		if (employee != null) {
			try {

				Employee savedEmployee = repository.save(employee);

				return savedEmployee;

			} catch (OptimisticLockingFailureException exception) {

				System.out.println("Hi i am getting errors");

				throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Optimistic Locking failure");

			}
		} else {

			System.out.println("Hi i am getting errors");

			throw new ServiceException(HttpStatus.BAD_REQUEST, "Request body is empty");
		}
	}
	
	

	public Employee update(Employee employee) throws ServiceException {
	    try {
	        if (employee != null) {
	            if (employee.getId() > 0) {
	                Optional<Employee> response = repository.findById(employee.getId());
	                
	                if (response.isPresent()) {
	                    Employee existingEmployee = response.get();
	                    
	                    existingEmployee.setEmpId(employee.getEmpId());
	                    existingEmployee.setDesignation(employee.getDesignation());
	                    
	                    return repository.save(existingEmployee);
	                } else {
	                    throw new ServiceException(HttpStatus.NOT_FOUND, "Employee with this ID not found");
	                }
	            } else {
	                throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid ID (must be > 0)");
	            }
	        } else {
	            throw new ServiceException(HttpStatus.BAD_REQUEST, "Employee object cannot be null");
	        }
	    } catch (Exception e) {
	        throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update employee");
	    }
	}
	
	
	public void deleteById(Integer id) throws ServiceException {
		if (id > 0) {
			Optional<Employee> response = repository.findById(id);

			if (response.isPresent()) {
				repository.deleteById(id);
			} else {
				throw new ServiceException(HttpStatus.NOT_FOUND, "Employee with this Id" + id + "not found");
			}

		} else {
			throw new ServiceException(HttpStatus.BAD_REQUEST, "Invalid Employee id : " + id);
		}
	}

}
