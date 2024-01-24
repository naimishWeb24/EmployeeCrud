<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp" %>
    <%@page import="com.EmployeeCrud.connection.DatabaseConnection" %>
    <%@page import="com.Dao.EmployeeDAO" %>
    <%@page import="java.sql.Connection" %>
    <%@page import="java.util.List" %>
    <%@page import="com.entity.Employee" %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	<%@page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
		
	
		<div class="container ">
			<div class="row">
				<div class="mt-3 mb-3">
					<center ><h1 >Employee Data</h1></center>
					
					<c:if test="${not empty successMessege }">
					<p class="text-center text-success">${successMessege }</p>
					<c:remove var="successMessege"/>
				</c:if>
				
				<c:if test="${not empty errorMessage }">
					<p class="text-center text-success">${errorMessage }</p>
					<c:remove var="errorMessage"/>
				</c:if>
				
				</div>
			</div>
			<div class="container-fluid">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Employee Name</th>
							<th scope="col">Skills</th>
							<th scope="col">Employee Age</th>
							<th scope="col">Employee Salary</th>
							<th scope="col">BirthDate</th>
							<th scope="col">Action</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
					
						<%
							EmployeeDAO dao = new EmployeeDAO(DatabaseConnection.getConnect());
							List<Employee> employeeList = dao.getAllEmployee();
							for(Employee e : employeeList) {
						%>
							<tr>
								<td scope="row"><%=e.getEmployeeID() %></td>
								<td scope="row"><%=e.getEmployeeName() %></td>
								<td scope="row"><%=e.getEmployeeSkills() %></td>
								<td scope="row"><%=e.getEmployeeAge() %></td>
								<td scope="row"><%=e.getEmployeeSalary() %></td>
								<td scope="row"><%=e.getEmployeeBirthdate() %></td>
								<td><a href="#" class="btn btn-primary">Edit</a></td>
								<td><a href="../DeleteServlet?id=<%=e.getEmployeeID() %>" class="btn btn-danger">Delete</a></td>
							</tr>	
						<%
							}
						%>
					</tbody>
					
						
				</table>
			</div>
		</div>

	
</body>
</html>