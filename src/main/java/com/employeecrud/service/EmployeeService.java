package com.employeecrud.service;

import java.util.List;
import java.util.Set;

import com.employeecrud.dao.EmployeeDaoImpl;
import com.employeecrud.entity.Employee;
import com.employeecrud.entity.Skills;

public interface EmployeeService {

	public void EmployeeServiceImpl(EmployeeDaoImpl employeeDao);
	
	public void addEmployee(Employee employee);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployeeById(int id);
	
	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(int id);

	boolean deleteSkills(int id);
	
	public boolean deleteSkillById(int id);
	
	 public Set<Skills> getSkillsByEmployeeId(int employeeId);

	public boolean addSkills(Skills skills, int employeeId);
}
