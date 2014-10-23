<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>List Movie</title>
</head>
<body>
<%@ include file="owner-header.jsp"%>

<form action="search" method="post">
<input name="keyword" type="text"> 
<input name="title" type="checkbox"> Title
<input name="genre" type="checkbox"> Genre
<input value="Search!" type="submit">
</form>
<h2>Search Results:</h2>
<table>
<tr><th width="150">Poster</th><th width="150">Title</th><th width="150">Date Added</th><th width="110">Add Showing Time</th></tr>
<tr align="center"><td><img alt="zz" src="posters/edge of tomorrow.jpg" height="150" width="100"></td><td>Edge of Tomorrow</td><td>Oct 12 2014 15:14 EST</td><td><a href="dispatcher?operation=showCinemaTimeline&movieid=1&cinemaid=2">Add</a></td></tr>
</table>
</body>
</html>