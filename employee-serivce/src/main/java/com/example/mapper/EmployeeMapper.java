package com.example.mapper;

import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;

public class EmployeeMapper {
	public static Employee mapToEmployee(EmployeeRequest employeeRequest) {
		return Employee.builder()
				.name(employeeRequest.getName())
				.designation(employeeRequest.getDesignation())
				.salary(employeeRequest.getSalary())
				.doj(employeeRequest.getDoj())
				.build();
	}
}
