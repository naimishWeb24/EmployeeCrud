package com.employeecrud.entity;

import java.util.Set;

public class Employee {
	private int employeeId;
	private String employeeName;
	private int employeeAge;
	private double employeeSalary;
	private String employeeBirthdate;
	private String employeeSkills;
	private int skillemployeeId;
	private int skillId;
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeAge=" + employeeAge
				+ ", employeeSalary=" + employeeSalary + ", employeeBirthdate=" + employeeBirthdate + ", skills=" + skills.toString() + ", employeeSkills=" + employeeSkills + ", skillemployeeId="
				+ skillemployeeId + ", skillId=" + skillId + "]";
	}

	private Set<Skills> skills;
	public Set<Skills> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skills> skills) {
		this.skills = skills;
	}
	
	public Employee() {
		super();
	}
	
	public Employee(String employeeName, int employeeAge, double employeeSalary,
			String employeeBirthdate) {
		super();
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.employeeSalary = employeeSalary;
		this.employeeBirthdate = employeeBirthdate;
	}
	
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public Employee(int employeeId ,String employeeName, int employeeAge, double employeeSalary,
			String employeeBirthdate) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.employeeSalary = employeeSalary;
		this.employeeBirthdate = employeeBirthdate;
		this.employeeId = employeeId;
	}
	
	public Employee(String employeeSkills,int employeeId) {
		super();
		
		this.employeeSkills = employeeSkills;
		this.employeeId = employeeId;
		
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getskillemployeeId() {
		return skillemployeeId;
	}
	
	public void setskillemployeeId(int i) {
		this.skillemployeeId = i;
	
	}
	
	public void setEmployeeSkills(String employeeSkills) {
		this.employeeSkills = employeeSkills;
	}
	
	public String getEmployeeSkills() {
		return employeeSkills;
	}
	
	public int getEmployeeAge() {
		return employeeAge;
	}
	
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public String getEmployeeBirthdate() {
		return employeeBirthdate;
	}
	
	public void setEmployeeBirthdate(String employeeBirthdate) {
		this.employeeBirthdate = employeeBirthdate;
	}
}
