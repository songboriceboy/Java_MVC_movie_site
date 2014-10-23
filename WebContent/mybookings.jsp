<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>View My Bookings</title>
</head>
<body>
<%@ include file="header.jsp"%>

<h2>My Bookings:</h2>
<table>
<tr><th width="100">Booking ID</th><th>Movie</th><th width="70">Cinema</th><th width="70">Date</th><th width="60">Time</th><th>No. of Tickets</th></tr>
<tr align="center"><td>123123</td><td><a href="dispatcher?operation=display?movieid=1">Edge of Tomorrow</a></td><td>George St</td><td>Oct 12</td><td>9:00am</td><td>2</td></tr>
</table>

</body>
</html>