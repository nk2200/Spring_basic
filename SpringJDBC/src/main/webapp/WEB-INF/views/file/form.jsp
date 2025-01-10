<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>file upload</h1>
<form action="./new" method="post" enctype="multipart/form-data">
카테고리 : <input type="text" name="category" required><p>
파일 : <input type="file" name="mfile" required><pr>
<input type="submit" value="저장">
<input type="reset" value="취소">
</form>
</body>
</html>