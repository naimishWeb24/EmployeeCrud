package com.employeecrud.service;

import java.util.List;
import java.util.Set;
import com.employeecrud.dao.EmployeeDao;
import com.employeecrud.dao.EmployeeDaoImpl;
import com.employeecrud.entity.Employee;
import com.employeecrud.entity.Skills;

public class EmployeeServiceImpl implements EmployeeService {
	 private EmployeeDao employeeDao;
	
	 public EmployeeServiceImpl(EmployeeDao employeeDao) {
	        this.employeeDao = employeeDao;
	 }
	 
	 public void addEmployee(Employee employee) {
		 employeeDao.addEmployee(employee);
		 for(Skills skills : employee.getSkills()) {
				  employeeDao.addSkills(skills,employee.getEmployeeId());
		 } 
	 }
	 
	 public List<Employee> getAllEmployee() {
		 return employeeDao.getAllEmployee();
	 }
	
	 public Employee getEmployeeById(int id) {
		 return employeeDao.getEmployeeById(id);
	 }
	 
	 public boolean updateEmployee(Employee employee) {
		 Set<Skills> newSkills = employee.getSkills();
		 Set<Skills> currentSkills = employeeDao.getSkillsByEmployeeId(employee.getEmployeeId());
		 for(Skills skills : newSkills) {
			 if(!currentSkills.contains(skills)) {
				  employeeDao.addSkills(skills,employee.getEmployeeId());
			 }
		 }
		 
		 for (Skills skill : currentSkills) {
		        if (!newSkills.contains(skill)) {
		        	int skillId = skill.getSkillId();
		            employeeDao.deleteSkillById(skillId);
		        }
		 }
		 return employeeDao.updateEmployee(employee);
	 }
	 
	 public boolean deleteEmployee (int id) {
		 return employeeDao.deleteEmployee(id);
	 }

	 public boolean addSkills(Skills skill,int employeeId) {
		 return employeeDao.addSkills(skill,employeeId);
	 }
	
	 public boolean deleteSkills(int id) {
		 return employeeDao.deleteSkills(id);
	 }
	 
	 public Set<Skills> getSkillsByEmployeeId(int employeeId) {
		 return employeeDao.getSkillsByEmployeeId(employeeId);
	 }
	 
	@Override
	public void EmployeeServiceImpl(EmployeeDaoImpl employeeDao) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean deleteSkillById(int id) {
		return employeeDao.deleteSkillById(id);
	}
}
