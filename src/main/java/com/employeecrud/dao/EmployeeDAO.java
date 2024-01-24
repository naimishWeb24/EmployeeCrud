package com.employeecrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employeecrud.entity.Employee;

public class EmployeeDAO {
		private Connection connection;
		public EmployeeDAO(Connection connection) {
			this.connection = connection;
		}
		
		public boolean addEmployee(Employee employee) {
			boolean employeeEntry = false;
			try {
					String insertQuery="Insert into Employee(employeeName,employeeSkills,employeeAge,employeeSalary,employeeBirthdate)VALUES(?,?,?,?,?)";
					PreparedStatement preparedstatement = connection.prepareStatement(insertQuery);
					preparedstatement.setString(1, employee.getEmployeeName());
					preparedstatement.setString(2, employee.getEmployeeSkills());
					preparedstatement.setInt(3, employee.getEmployeeAge());
					preparedstatement.setDouble(4, employee.getEmployeeSalary());
					preparedstatement.setString(5, employee.getEmployeeBirthdate());
					int count = preparedstatement.executeUpdate();						
					if (count == 1) {
						employeeEntry = true;
					}
					connection.close();
				} catch (SQLException exception) {
					exception.printStackTrace();
			}
			return employeeEntry;
		}
		
		public List<Employee> getAllEmployee() {
			List<Employee> employeeList = new ArrayList<Employee>();
			Employee employee = null;
			try {
					String selectQuery = "Select * From Employee";
					PreparedStatement preparedstatement = connection.prepareStatement(selectQuery);
					ResultSet resultset = preparedstatement.executeQuery();
					int count = 0;
					while(resultset.next()) {
						employee = new Employee();
						employee.setEmployeeId(resultset.getInt(1));
						employee.setEmployeeName(resultset.getString(2));
						employee.setEmployeeSkills(resultset.getString(3));
						employee.setEmployeeAge(resultset.getInt(4));
						employee.setEmployeeSalary(resultset.getDouble(5));
						employee.setEmployeeBirthdate(resultset.getString(6));
						employeeList.add(employee);
						count++;
					}
				} catch(Exception exception) {
					exception.printStackTrace();
			    }
			return employeeList;	
		}
		
		public Employee getEmployeeById(int id) {
				Employee employee = null;
			try {
					String fetchId ="Select * from Employee where employeeId = ?";
					PreparedStatement preparedstatement = connection.prepareStatement(fetchId);
					preparedstatement.setInt(1, id);
					ResultSet resultset = preparedstatement.executeQuery();
					while (resultset.next()) {
						employee = new Employee();
						employee.setEmployeeId(resultset.getInt(1));
						employee.setEmployeeName(resultset.getString(2));
						employee.setEmployeeSkills(resultset.getString(3));
						employee.setEmployeeAge(resultset.getInt(4));
						employee.setEmployeeSalary(resultset.getDouble(5));
						employee.setEmployeeBirthdate(resultset.getString(6));
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			return employee;
		}
		
		public boolean updateEmployee(Employee employee) {
			boolean isUpdated = false;
			try {
					String updateQuery = "update Employee set employeeName=?,employeeSkills=?,employeeAge=?,employeeSalary=?,employeeBirthdate=? where employeeId=?";
					PreparedStatement preparedstatement = connection.prepareStatement(updateQuery);
					preparedstatement.setString(1, employee.getEmployeeName());
					preparedstatement.setString(2, employee.getEmployeeSkills());
					preparedstatement.setInt(3, employee.getEmployeeAge());
					preparedstatement.setDouble(4, employee.getEmployeeSalary());
					preparedstatement.setString(5, employee.getEmployeeBirthdate());
					preparedstatement.setInt(6, employee.getEmployeeId());
					int count = preparedstatement.executeUpdate();
					if (count == 1) {
						isUpdated = true;
					}
					connection.close();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			return isUpdated;
		}
		
		public boolean deleteEmployee (int id) {
			boolean isDeleted = false;
			try {
					String deleteQuery = "delete from Employee where employeeId=?";
					PreparedStatement preparedstatement = connection.prepareStatement(deleteQuery);
					preparedstatement.setInt(1, id);
					int count = preparedstatement.executeUpdate();
					if (count == 1) {
						isDeleted = true;
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			return isDeleted;
		}
} 
