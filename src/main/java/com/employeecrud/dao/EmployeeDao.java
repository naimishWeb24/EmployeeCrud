package com.employeecrud.dao;

import java.util.List;
import java.util.Set;

import com.employeecrud.entity.Employee;
import com.employeecrud.entity.Skills;

public interface EmployeeDao {
	
	void addEmployee(Employee employee);
	
	List<Employee> getAllEmployee();
	
	Employee getEmployeeById(int id);
	
	boolean updateEmployee(Employee employee);
	
	boolean deleteEmployee (int id);
	
	public boolean deleteSkillById(int id);
	
	boolean deleteSkills(int id);
	
	Set<Skills> getSkillsByEmployeeId(int employeeId);

	boolean addSkills(Skills skill, int employeeId);
	
}
