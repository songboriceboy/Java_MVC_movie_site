<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Add Cinema</title>
</head>
<body>
<%@ include file="owner-header.jsp"%>

<form action="dispatcher?operation=addCinema">
<table>
<tr><td><b>Cinema Name:</b></td><td><input type="text" name="name"></td></tr>
<tr><td><b>Seat Capacity:</b></td><td><input type="text" name="capacity"></td></tr>
<tr><td><b>Single Ticket Price:</b></td><td>$<input type="text" name="ticketprice"></td></tr>
<tr><td></td><td><input type="submit" value="Add Cinema!"></td></tr>
</table>
</form>

</body>
</html>