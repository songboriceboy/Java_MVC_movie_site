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

<table>
<tr><td width="100"><b>Movie Title:</b></td><td>Edge of Tomorrow</td></tr>
<tr><td><b>Cinema:</b></td><td>
<form action="dispatcher?operation=showCinemaTimeline&movieid=1" method="post">
<select name="cinemaid">
  <option value="3">George St</option>
  <option value="1" selected>Burwood</option>
</select>
<input type="submit" value="Change Cinema">
</form>
</td></tr></table>

<form action="dispatcher?operation=addMovieTime&movieid=1" method="post">
<table>
<tr><td width="100"><b>Start Date:</b></td><td><input type="text" name="startdate">(YY-MM-DD or MM-DD)</td></tr>
<tr><td><b>End Date:</b></td><td><input type="text" name="enddate"></td></tr>
<tr><td><b>Timeslot:</b></td><td>
<select name="timeslot">
  <option value="1">9:00am</option>
  <option value="2">12:00pm</option>
</select>
</td></tr>
<tr><td></td><td><input type="hidden" name="cinemaid" value="1"><input type="submit" value="Add Time"></td></tr>
</table>
</form>

<h3>Screening Schedule - Burwood Cinema</h3>
<table border="1">
<tr align="center" valign="middle"><td width="50"></td><td width="80">Oct 1, 2014</td><td width="80">Oct 2</td><td width="80">Oct 3</td><td width="80">Oct 4</td><td width="80">Oct 5</td><td width="80">Oct 6</td><td width="80">Oct 7</td></tr>
<tr align="center" valign="middle"><td height="40">09:00</td><td colspan="3" bgcolor="#DAC8FF">The Matrix</td><td>Oct 4</td><td>Oct 5</td><td>Oct 6</td><td>Oct 7</td></tr>
<tr align="center" valign="middle"><td height="40">12:00</td><td>&nbsp;</td><td colspan="4" bgcolor="#DAC8FF">The Edge of Tomorrow</td><td>Oct 6</td><td>Oct 7</td></tr>
<tr align="center" valign="middle"><td height="40">15:00</td><td>&nbsp;</td><td>&nbsp;</td><td colspan="3" bgcolor="#DAC8FF">Source Code</td><td>Oct 6</td><td>Oct 7</td></tr>
<tr align="center" valign="middle"><td height="40">18:00</td><td colspan="3" bgcolor="#DAC8FF">The Matrix</td><td>Oct 4</td><td>Oct 5</td><td>Oct 6</td><td>Oct 7</td></tr>
</table>

</body>
</html>