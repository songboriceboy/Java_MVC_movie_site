<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Add Movie</title>
</head>
<body>
<%@ include file="owner-header.jsp"%>

<form action="dispatcher?operation=addMovie">
<table>
<tr><td><b>Movie Title:</b></td><td><input type="text" name="title"></td></tr>
<tr><td><b>Poster:</b></td><td><input type="text" name="poster"></td></tr>
<tr><td><b>Main Actors:</b></td><td><input type="text" name="actors"></td></tr>
<tr><td><b>Genre:</b></td><td>
<select name="genre">
<option>Sci Fi</option>
<option>Fantacy</option>
</select>
</td></tr>
<tr><td><b>Director:</b></td><td><input type="text" name="director"></td></tr>
<tr><td><b>Short Synopsis:</b><br/>(about 100 words)</td><td><textarea name="synopsis" rows="5" cols="30"></textarea></td></tr>
<tr><td></td><td><input type="submit" value="Add Movie"></td></tr>
</table>
</form>

</body>
</html>