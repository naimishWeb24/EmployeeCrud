package com.employeecrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.employeecrud.entity.Employee;
import com.employeecrud.entity.Skills;

public class EmployeeDaoImpl implements EmployeeDao {
		private Connection connection;
		
		public EmployeeDaoImpl(Connection connection) {
			this.connection = connection;
		}
		
		public void addEmployee(Employee employee) {
			try {
				String insertQuery="Insert into Employee(employeeName,employeeAge,employeeSalary,employeeBirthdate)VALUES(?,?,?,?)";
				PreparedStatement preparedstatement = connection.prepareStatement(insertQuery,java.sql.Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setString(1, employee.getEmployeeName());
				preparedstatement.setInt(2, employee.getEmployeeAge());
				preparedstatement.setDouble(3, employee.getEmployeeSalary());
				preparedstatement.setString(4, employee.getEmployeeBirthdate());
				int count = preparedstatement.executeUpdate();						
				if (count > 0) {
					ResultSet generatedKeys = preparedstatement.getGeneratedKeys();
					if(generatedKeys.next()) { 
						employee.setEmployeeId(generatedKeys.getInt(1));
					}
					generatedKeys.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public boolean addSkills(Skills skill,int employeeId) {
			try {
				String query="Insert into tableSkill(skills,employeeId)VALUES(?,?)";
				PreparedStatement preparedstatement = connection.prepareStatement(query);
				preparedstatement.setString(1, skill.getSkill());
				preparedstatement.setInt(2,employeeId);
				preparedstatement.executeUpdate();			
			} catch (SQLException exception) {
					exception.printStackTrace();
			}
			return true;
		}
		
		public List<Employee> getAllEmployee() {
			List<Employee> employeeList = new ArrayList<Employee>();
			Employee employee = null;
			try {
				String selectQuery = "select * from Employee";
				PreparedStatement preparedstatement = connection.prepareStatement(selectQuery);
				ResultSet resultset = preparedstatement.executeQuery();
				while(resultset.next()) {
					employee = new Employee();
					employee.setEmployeeId(resultset.getInt(1));
					employee.setEmployeeName(resultset.getString(2));
					employee.setEmployeeAge(resultset.getInt(3));
					employee.setEmployeeSalary(resultset.getDouble(4));
					employee.setEmployeeBirthdate(resultset.getString(5));
					employee.setSkills(getSkillsByEmployeeId(resultset.getInt(1)));
					employeeList.add(employee);
				}
			} catch(Exception exception) {
				exception.printStackTrace();
		    }
			return employeeList;	
		}
	
		public Set<Skills> getSkillsByEmployeeId(int employeeId) {
			Set<Skills> skillList = new HashSet<Skills>();
			Skills skill = null;
			try {
				String selectQuery = " Select * from tableSkill where employeeId="+employeeId;
				PreparedStatement preparedstatement = connection.prepareStatement(selectQuery);
				ResultSet rs=preparedstatement.executeQuery();
				while (rs.next()) {
					skill =new Skills();
					skill.setSkillId(rs.getInt(1));
					skill.setSkill(rs.getString(2));
					skillList.add(skill);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return skillList;
		}

		public Employee getEmployeeById(int id) {
				Employee employee = null;
			try {
				String fetchId ="Select Employee.employeeId, Employee.employeeName, Employee.employeeAge, Employee.employeeSalary, Employee.employeeBirthdate, tableSkill.skills from Employee INNER JOIN tableSkill where Employee.employeeId = tableSkill.employeeId and Employee.employeeId=?";
				PreparedStatement preparedstatement = connection.prepareStatement(fetchId);
				preparedstatement.setInt(1, id);
				ResultSet resultset = preparedstatement.executeQuery();
				while (resultset.next()) {
					employee = new Employee();
					employee.setEmployeeId(resultset.getInt(1));
					employee.setEmployeeName(resultset.getString(2));
					employee.setEmployeeAge(resultset.getInt(3));
					employee.setEmployeeSalary(resultset.getDouble(4));
					employee.setEmployeeBirthdate(resultset.getString(5));
					employee.setSkills(getSkillsByEmployeeId(resultset.getInt(1)));
				}
			} catch (Exception exception) {
					exception.printStackTrace();
			}
			return employee;
		}
		
		public boolean updateEmployee(Employee employee) {
			boolean isUpdated = false;
			try {
				String updateQuery = "update Employee set employeeName=?,employeeAge=?,employeeSalary=?,employeeBirthdate=? where employeeId=?";
				PreparedStatement preparedstatement = connection.prepareStatement(updateQuery);
				preparedstatement.setString(1, employee.getEmployeeName());
				preparedstatement.setInt(2, employee.getEmployeeAge());
				preparedstatement.setDouble(3, employee.getEmployeeSalary());
				preparedstatement.setString(4, employee.getEmployeeBirthdate());
				preparedstatement.setInt(5, employee.getEmployeeId());
				int count = preparedstatement.executeUpdate();
				if (count == 1) {
					isUpdated = true;
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			return isUpdated;
		}
		
		public boolean deleteEmployee (int id) {
			boolean isDeleted = false;
			try {
				String deleteQuery = "Delete from Employee where employeeId=?";
				PreparedStatement preparedstatement = connection.prepareStatement(deleteQuery);
				preparedstatement.setInt(1, id);
				int count = preparedstatement.executeUpdate();
				if (count == 1) {
					isDeleted = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isDeleted;
		}
		
		public boolean deleteSkills(int id) {
			boolean isDeleted =false;
			try {
				String deleteSkillQuery = "Delete from tableSkill where employeeId=?";
				PreparedStatement preparedstatement = connection.prepareStatement(deleteSkillQuery);
				preparedstatement.setInt(1, id);
				int count = preparedstatement.executeUpdate();
				if (count == 1) {
					isDeleted = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isDeleted;
		}
		
		public boolean deleteSkillById(int id) {
			try {
				String deleteSkillQuery = "DELETE FROM tableSkill WHERE skillId=?";
				PreparedStatement preparedstatement = connection.prepareStatement(deleteSkillQuery);
				preparedstatement.setInt(1, id);
				int count = preparedstatement.executeUpdate();
				return count > 0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
} 
