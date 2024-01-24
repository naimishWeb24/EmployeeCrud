<%@ include file="View/header.jsp" %>
<!-- JSTL Core Uri --> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Home-Page</title>
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
					<h3>Enter Employee Details</h3>
				</div>
				<div class="card-body" >
				<c:if test="${not empty insertMessage }">
					<p class="text-center text-success">${insertMessage }</p>
					<c:remove var="insertMessage"/>
				</c:if>
				<c:if test="${not empty errorMessage }">
					<p class="text-center text-success">${errorMessage }</p>
					<c:remove var="errorMessage"/>
				</c:if>	
					<form action="InsertEmployeeServlet" method="post" onsubmit="return validateAge()">
							<input type="hidden" name="action" value="insert">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Name :</label>
							<input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Skills:</label><br>
							<input type="checkbox" name="skills" class="" id="Java"   value="Java" aria-describedby="emailHelp" >
							<label for="Java"> Java</label><br>
							<input type="checkbox" name="skills" class="" id="Python"  value="Python" aria-describedby="emailHelp" >
							<label for="Python"> Python</label><br>
							<input type="checkbox" name="skills" class="" id="Ios Development"  value="Ios Development" aria-describedby="emailHelp" >
							<label for="Ios Development"> Ios Development</label><br>
							<input type="checkbox"  name="skills" class="" id="Reactjs"  value="Reactjs" aria-describedby="emailHelp">
							<label for="Reactjs"> ReactJS</label><br>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Age :</label>
							<input type="text" name="age" class="form-control" id="age" aria-describedby="emailHelp" required>
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Employee Salary :</label>
							<input type="number" name="salary" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required >
						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">BirthDate :</label>
							<input type="date" name="birthdate" class="form-control" id="exampleInputEmail1" max="<%= java.time.LocalDate.now() %>" required>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
</html>