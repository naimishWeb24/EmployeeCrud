package com.employeecrud.entity;

public class Employee {
	private int employeeId;
	private String employeeName;
	private String employeeSkills;
	private int employeeAge;
	private double employeeSalary;
	private String employeeBirthdate;
	
	public Employee() {
		super();
	}
	
	public Employee(String employeeName, String employeeSkills, int employeeAge, double employeeSalary,
			String employeeBirthdate) {
		super();
		this.employeeName = employeeName;
		this.employeeSkills = employeeSkills;
		this.employeeAge = employeeAge;
		this.employeeSalary = employeeSalary;
		this.employeeBirthdate = employeeBirthdate;
	}
	
	public Employee(int employeeId ,String employeeName, String employeeSkills, int employeeAge, double employeeSalary,
			String employeeBirthdate) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSkills = employeeSkills;
		this.employeeAge = employeeAge;
		this.employeeSalary = employeeSalary;
		this.employeeBirthdate = employeeBirthdate;
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
	public String getEmployeeSkills() {
		return employeeSkills;
	}
	
	public void setEmployeeSkills(String employeeSkills) {
		this.employeeSkills = employeeSkills;
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
