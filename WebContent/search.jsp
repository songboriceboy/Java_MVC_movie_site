<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Search Movie</title>
</head>
<body>
<%@ include file="header.jsp"%>

<form action="search" method="post">
<input name="keyword" type="text"> 
<input name="title" type="checkbox"> Title
<input name="genre" type="checkbox"> Genre
<input value="Search!" type="submit">
</form>
<h2>Search Results:</h2>
<table>
<tr><th width="150">Poster</th><th width="150">Title</th><th width="150">Genre</th><th width="150">Main Actors</th><th width="100">Rating</th><th width="120">Status</th><th width="100">Details</th></tr>
<tr align="center"><td><img alt="zz" src="posters/edge of tomorrow.jpg" height="150" width="100"></td><td>Edge of Tomorrow</td><td>Science Fiction</td><td>Tom Hanks</td><td>4.5<br/>(12 Reviews)</td><td>Coming Soon on<br/>Oct 19 2014</td><td><a href="display?id=1">Details</a></td></tr>
</table>
</body>
</html>