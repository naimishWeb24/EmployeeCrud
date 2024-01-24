package com.employeecrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employeecrud.connection.DatabaseConnection;
import com.employeecrud.dao.EmployeeDAO;
import com.employeecrud.entity.Employee;

//Employee Controller
public class InsertEmployeeServlet extends HttpServlet {
	EmployeeDAO dao = new EmployeeDAO(DatabaseConnection.getConnect());
	
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
	        } else {
	            // Unknown action, show employee list
	            listEmployees(request, response);
	        }
		response.getWriter().append("Served at: ").append(request.getContextPath());		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		 if ("insert".equals(action)) {						
	            // Add action
			 insertEmployee(request, response);
	        } else if ("update".equals(action)) {
	            // Update action
	        	updateEmployee(request, response);
	        } else {
	            // Unknown action, show employee list
	            listEmployees(request, response);
	        }
	}
	// Insert Method
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	 
			HttpSession session =request.getSession();
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();
			String employeeName = request.getParameter("name");
			String[] employeeSkills = request.getParameterValues("skills");
			String skillsString =String.join(",",employeeSkills);
			String employeeAge = request.getParameter("age");
			int empAge = Integer.parseInt(employeeAge);
            String employeeSalary = request.getParameter("salary");
            double empSalary = Double.parseDouble(employeeSalary);
            String birthDate = request.getParameter("birthdate");
            Employee employee = new Employee(employeeName, skillsString, empAge, empSalary, birthDate);
            EmployeeDAO employeeDao = new EmployeeDAO(DatabaseConnection.getConnect());
            boolean isPassed = employeeDao.addEmployee(employee);

	        if (isPassed) {
	                session.setAttribute("insertMessage", "Employee Details Submitted Successfully...");
	                session.removeAttribute("errorMessage");
	                response.sendRedirect("index.jsp");
	        } else {
		            session.setAttribute("errorMessage", "Something Went Wrong on Server");
		            session.removeAttribute("insertMessage");
		            System.out.println("Something Went Wrong on Server");
	        }
	 }
		// Display Method
	 private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Employee> employeeList = dao.getAllEmployee();
	        request.setAttribute("employeeList", employeeList);
	        request.getRequestDispatcher("/View/display.jsp").forward(request, response);
	 }
	  // edit Method
    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = dao.getEmployeeById(employeeId);
        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/View/update.jsp").forward(request, response);   
    }
	// Update Method
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session =request.getSession();
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		String employeeName = request.getParameter("name");
		String[] employeeSkills = request.getParameterValues("skills");
		String skillsString =String.join(",",employeeSkills);
		String employeeAge = request.getParameter("age");
		int empAge = 0;
		    try {
		           empAge = Integer.parseInt(employeeAge);
		        // Check if the age is within the specified range
		        if (empAge >= 18 && empAge <= 60) {
		            String employeeSalary = request.getParameter("salary");
		            double empSalary = Double.parseDouble(employeeSalary);
		            int employeeId = Integer.parseInt(request.getParameter("id"));
		            String birthDate = request.getParameter("birthdate");
		            Employee employee = new Employee(employeeId,employeeName,skillsString,empAge,empSalary,birthDate);
		            EmployeeDAO employeeDao = new EmployeeDAO(DatabaseConnection.getConnect());
		            employeeDao.updateEmployee(employee);
		            boolean isPassed = employeeDao.addEmployee(employee);

		            if (isPassed) {
		                session.setAttribute("insertMessage", "Employee Details Submitted Successfully...");
		                response.sendRedirect("index.jsp");
		            } else {
		                session.setAttribute("errorMessage", "Something Went Wrong on Server");
		                System.out.println("Something Went Wrong on Server");
		            }
		        } else {
		            session.setAttribute("errorAge", "Please enter an age between 18 to 60.");
		            response.sendRedirect("index.jsp"); // Redirect back to the form page
		        }
		    } catch (NumberFormatException e) {
		        session.setAttribute("errorAge", "Please enter a valid age.");
		        response.sendRedirect("index.jsp"); // Redirect back to the form page
		    }
		EmployeeDAO employeeDao = new EmployeeDAO(DatabaseConnection.getConnect());
		boolean isUpdated = true; 
		if (isUpdated == true) {
			session.setAttribute("updateMessage", "Employee Updated Sucesfully...");
			session.removeAttribute("errorMessage");
			response.sendRedirect(request.getContextPath() + "/InsertEmployeeServlet");
		} else {
			session.setAttribute("errorMessage", "Something Went Wrong on Server");
			session.removeAttribute("updateMessage");
			   }
    }
    // Delete Method
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        HttpSession session =request.getSession();
        boolean success = dao.deleteEmployee(employeeId);
        if (success) { 	
            response.sendRedirect(request.getContextPath() + "/InsertEmployeeServlet");
        } else {
            // Handle error.
        	session.setAttribute("errorMessage", "Somthing Went Wrong on Server");
            response.sendRedirect(request.getContextPath() + "/InsertEmployeeServlet");
        }		
    }
}
