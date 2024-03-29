package com.example.report;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeReport {

	private static final String PATH = "C:\\Report";

	@Autowired
	private EmployeeRepository employeeRepository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

		List<Employee> employees = employeeRepository.findAll();

		String file = ResourceUtils.getFile("classpath:employees.jrxml")
				.getAbsolutePath();

		JasperReport jasperReport = JasperCompileManager.compileReport(file);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Java Developer");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH + "\\employees.html");
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, PATH + "\\employees.pdf");
		} else if (reportFormat.equalsIgnoreCase("xml")) {
			JasperExportManager.exportReportToXmlFile(jasperPrint, PATH + "\\employees.xml", false);
		}

		return "report generated in path : " + PATH;
	}
}