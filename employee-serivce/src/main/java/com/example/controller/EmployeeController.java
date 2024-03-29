package com.example.controller;

import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;
import com.example.report.EmployeeReport;
import com.example.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeReport employeeReport;

	@GetMapping("/")
	public List<Employee> getEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("/")
	public void createEmployee(@RequestBody EmployeeRequest productRequest) {
		employeeService.createEmployee(productRequest);
	}

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return employeeReport.exportReport(format);
	}
}
