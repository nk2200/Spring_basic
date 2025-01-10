<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>사원 정보 입력</h2>
<form action="./insert" method="post">
	<table border="1">
	<tr>
		<th>Employee_id</th>
		<td><input type="number" name="employeeId" required></td>
	</tr>
	<tr>
		<th>First_name</th>
		<td><input type="text" name="firstName" required></td>
	</tr>
	<tr>
		<th>Last_name</th>
		<td><input type="text" name="lastName" required></td>
	</tr>
	<tr>
		<th>email</th>
		<td><input type="text" name="email" required></td>
	</tr>
	<tr>
		<th>Hire_date</th>
		<td><input type="date" name="hireDate" required></td>
	</tr>
	<tr>
		<th>Job_id</th>
		<td>
			<select name="jobId">
			<c:forEach var="job" items="${jobList }">
				<option value="${job.job_id }">${job.job_title }</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>salary</th>
		<td><input type="number" name="salary" required></td>
	</tr>
	<tr>
		<th>commision_pct</th>
		<td><input type="double" name="commissionPct" required></td>
	</tr>
	<tr>
		<th>Manager_id</th>
		<td>
			<select name="managerId">
			<c:forEach var="manager" items="${managerList }">
				<option value="${manager.manager_id }">${manager.name }</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>department_id</th>
		<td>
		<select name="departmentId">
			<c:forEach var="department" items="${deptList }">
				<option value="${department.DEPARTMENT_ID }">${department.department_name }</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	
	</table>
	<hr>
	<input type="submit" value="저장">
	<input type="reset" vlaue="취소" onclick="history.back()">
</form>
</body>
</html>