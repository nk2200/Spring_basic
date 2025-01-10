<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var deleteLinks = document.querySelectorAll(".delete");
		console.log(deleteLinks);
		for(let i=0; i<deleteLinks.length; i++){
			console.log(deleteLinks[0]);
			deleteLinks[i].onclick = function(){
				console.log(deleteLinks[i]);
				if(confirm("정말 삭제하시겠습니까?")){
					return true;
				}else{
					return false;
				}
			}
		}
		
		/* alert("ddd");
			var fileId = `${file.fileId}`;
			console.log(fileId);
			$('.delete').click(function() {
				if (confirm("정말 삭제하시겠습니까?")) {
					window.location.href = "./delete/"+fileId;
				} else {
					e.preventDefault();
				}
			}); */
		
	});

</script>
</head>
<body>
	<h1>파일 목록</h1>
	<a href="new">업로드</a>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>분류</th>
			<th>파일명</th>
			<th>크기</th>
			<th>유형</th>
			<th>날짜/시간</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="file" items="${fileList }">
			<tr>
				<td>${file.fileId }</td>
				<td>${file.categoryName }</td>
				<td><a href="./${file.fileId }">${file.fileName }</a> 
				<img src="${file.fileName }" /></td>
				<td>${file.fileSize }</td>
				<td>${file.fileContentType }</td>
				<td>${file.fileUploadDate }</td>
				<td><a href="./delete/${file.fileId }" class="delete">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>