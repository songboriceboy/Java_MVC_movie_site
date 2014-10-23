<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Update My Details</title>
</head>
<body>
<%@ include file="header.jsp"%>

<h2>My Details:</h2>
<form action="dispatcher?operation=updatedetails" method="post">
<table>
<tr><td>User name:</td><td>czha483</td></tr>
<tr><td>First name:</td><td><input type="text" name="firstname" value="zz"></td></tr>
<tr><td>Last name:</td><td><input type="text" name="lastname" value="zz"></td></tr>
<tr><td>Nick name:</td><td><input type="text" name="nickname" value="zz"></td></tr>
<tr><td>Email:</td><td><input type="text" name="email" value="zz" size="40"></td></tr>
<tr><td></td><td><input type="submit" value="Update!"></td></tr>
<tr><td></td><td>Update Successful!</td></tr>
</table>
</form>

</body>
</html>