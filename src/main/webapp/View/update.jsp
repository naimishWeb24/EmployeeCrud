<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="header.jsp" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Edit Employee</title>
</head>
<body>
 <script>
        function validateAge() {
            var empAge = document.getElementById('age').value;
            // Validate age (between 18 and 60)
             if (!(empAge >= 18 && empAge <= 60)) {
                alert('Please enter a valid age between 18 and 60.');
                return false;
            }
            return true;
        }
    </script>
	<div class="container">
		<div class="container-fluid">
			<div class="col-md-7	">
			<div class="card mt-5">
				<div class="card-header text-center  ">
					<h3>Update Employee Details</h3>		
				</div>
				<div class="card-body" >
				  	<form action="/EmployeeCrud/InsertEmployeeServlet" onsubmit="return validateAge()" method="post">
				  		 <input type="hidden" name="action" value="update">
				  		   <c:if test="${not empty employee}">
				  		  	<input type="hidden" name="id" value="${employee.employeeId}">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Name :</label>
							<input type="text" name="name" value="${employee.employeeName}" class="form-control"  >
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Skills:</label><br>
							<c:set var="skillsArray" value="${fn:split(employee.employeeSkills, ',')}" />
									<input type="checkbox" name="skills"   class="" id="Java" value="Java"${fn:contains(employee.employeeSkills, 'Java') ? 'checked' : ''}>
									<label for="Java"> Java</label><br>
									
									<input type="checkbox" name="skills"  class="" id="Python"   value="Python" ${fn:contains(employee.employeeSkills, 'Python') ? 'checked' : ''}  >
									<label for="Python"> Python</label><br>
									<input type="checkbox" name="skills"  class="" id="Ios Development"   value="Ios Development" ${fn:contains(employee.employeeSkills, 'Ios Development') ? 'checked' : ''} >
									<label for="Ios Development"> Ios Development</label><br>
									<input type="checkbox"  name="skills"  class="" id="Reactjs"  value="Reactjs" ${fn:contains(employee.employeeSkills, 'Reactjs') ? 'checked' : ''} >
									<label for="Reactjs"> ReactJS</label><br>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Age :</label>
							<input type="number" name="age" value="${employee.employeeAge}" class="form-control"required>
						</div>
						
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Salary :</label>
							<input type="number" name="salary" value="${employee.employeeSalary}" class="form-control"  required>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">BirthDate :</label>
							<input type="date" name="birthdate" value="${employee.employeeBirthdate}" class="form-control"  max="<%= java.time.LocalDate.now() %>" required>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</c:if>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>