package com.employeecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.employeecrud.connection.DatabaseConnection;
import com.employeecrud.dao.EmployeeDaoImpl;
import com.employeecrud.entity.Employee;
import com.employeecrud.entity.Skills;
import com.employeecrud.service.EmployeeServiceImpl;
import com.employeecrud.service.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	
	private EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl(DatabaseConnection.getConnect()));
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {							
			listEmployees(request,response);
		} else if ("edit".equals(action)) {
	            // Edit action
			editEmployee(request,response);
        } else if ("delete".equals(action)) {
            // Delete action
        	deleteEmployee(request, response);
        } 
		response.getWriter().append("Served at: ").append(request.getContextPath());		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("insert".equals(action)) {						
			 insertEmployee(request, response);
	    } else if ("update".equals(action)) {
	        	updateEmployee(request, response);
	    }
	}
	
	// Insert Method
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	 
		HttpSession session =request.getSession();
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		String employeeName = request.getParameter("name");
		String[] skillsArray = request.getParameterValues("skills");
		Set<Skills> skillsSet = new HashSet<>();
        if (skillsArray != null) {
            for (String skillName : skillsArray) {
                Skills skill = new Skills(skillName);
                skillsSet.add(skill);    
            }
        }
		String employeeAge = request.getParameter("age");
		int empAge = Integer.parseInt(employeeAge);
        String employeeSalary = request.getParameter("salary");
        double empSalary = Double.parseDouble(employeeSalary);
        String birthDate = request.getParameter("birthdate");
        Employee employee = new Employee(employeeName, empAge, empSalary, birthDate);
        employee.setSkills(skillsSet);
        employeeService.addEmployee(employee);
        response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
	 }
	
	// Display Method
	 private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EmployeeDaoImpl dao = new EmployeeDaoImpl(DatabaseConnection.getConnect());
		 List<Employee> employeeList = employeeService.getAllEmployee();
		 request.setAttribute("employeeList", employeeList);
		 request.getRequestDispatcher("/View/display.jsp").forward(request, response);       
	 }
	 
	 // edit Method
    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/View/insert.jsp").forward(request, response);   
    }
    
	// Update Method
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session =request.getSession();
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		int employeeId = Integer.parseInt(request.getParameter("id"));
		String employeeName = request.getParameter("name");	
		String[] skillsArray = request.getParameterValues("skills");
        Set<Skills> skillsSet = new HashSet<>();
        if (skillsArray != null) {
            for (String skillName : skillsArray) {
                Skills skill = new Skills(skillName);
                skillsSet.add(skill);    
            }
        }
		String employeeAge = request.getParameter("age");
		int empAge = Integer.parseInt(employeeAge);
        String employeeSalary = request.getParameter("salary");
        double empSalary = Double.parseDouble(employeeSalary);
        String birthDate = request.getParameter("birthdate");
        Employee employee = new Employee(employeeId,employeeName, empAge, empSalary, birthDate);
        employee.setSkills(skillsSet);
        boolean isPassed = employeeService.updateEmployee(employee);
	    if (isPassed == true) {
				response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
		} else {
				session.removeAttribute("updateMessage");
		}
	}
    
    // Delete Method
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        HttpSession session =request.getSession();
        employeeService.deleteSkills(employeeId);
        boolean success = employeeService.deleteEmployee(employeeId);
        if (success == true) { 	
            response.sendRedirect(request.getContextPath() + "/EmployeeServlet");  
        } else {
            // Handle error.
           response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
        }		
    }
}
