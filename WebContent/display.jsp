<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Movie Name</title>
</head>
<body>
<%@ include file="header.jsp"%>

<table width="700"><tr valign="top">
<td width="280"><img alt="zz" src="posters/edge of tomorrow.jpg" height="400" width="266"></td>
<td><table>
<tr><td> <font size="6"><b>Edge of Tomorrow</b></font><!-- </br> Science Fiction | Start showing on Oct 19 2014</br> Rating: <strong>4.5</strong> by 12 users. --> </td></tr>
<tr><td><p>An officer finds himself caught in a time loop in a war with the alien race. His skills increase as he faces the same brutal combat scenarios, and his union with a Special Forces warrior gets him closer to defeating the enemy.</p></td></tr>
<tr><td><table>
<tr><td><b>Director:</b></td><td>Doug Liman</td></tr>
<tr><td><b>Main Actors:</b></td><td>Tom Cruise, Emily Blunt, Bill Paxton</td></tr>
<tr><td><b>Genre:</b></td><td>Science Fiction</td></tr>
<tr><td><b>Rating:</b></td><td><strong>4.5</strong> by 12 users.</td></tr>
<tr><td><b>Start Date:</b></td><td>Oct 19 2014</td></tr>
<tr><td><b>End Date:</b></td><td>Nov 19 2014</td></tr>
<tr><td><b>Tickets:</b></td><td><a href="booking?movieid=1">Book Now!</a></td></tr>
</table></td></tr>
</table></td>
</tr></table>

<table  width="700">
<tr><td><h2 style="font-family: verdana; color: orange;">Reviews:</h2></td></tr>
<tr><td><table>
<tr><td><b>Mainstream Hollywood can get it right when it tries</b></br> <strong>Rating 5</strong> | by Adam Peters at Sep 9 2014 15:34 EST </td></tr>
<tr><td><p>Mr Tom Cruise's good form continues with this fine, fun sci-fi action flick that could be likened to a video game in the best possible sense of the term. The action set-pieces themselves are really very well done, and like all good big blockbuster movies this is hugely enjoyable throughout, with an interesting, if not entirely faultless plot. The fact that this pulls the Groundhog day trick that as it happens didn't fill every person that went to go see it with a huge groan of boredom is in itself quite an accomplishment. And with Godzilla, X-men and now this, mainstream Hollywood is being quite generous these past few weeks. Yet another big- screen must-watch crowd-pleaser.</p></td></tr>
</table></td></tr>
</table>

<table width="700">
<tr><td><h2 style="font-family: verdana; color: orange;">Post My Review:</h2></td></tr>
<form action="review" method="post">
<tr><td><table>
<tr valign="top"><td><b>Title:</b></td><td><input name="title" type="text" size="70"></td></tr>
<tr valign="top"><td><b>Rating:</b></td><td><input name="rating" type="text" size="4" maxlength="1">(1 to 5)</td></tr>
<tr valign="top"><td><b>Comment:</b></td><td><textarea name="comment" rows="7" cols="80">Add your comment here!</textarea></td></tr>
</table></td></tr>
<tr><td><input type="submit" value="Post Review!"></td></tr>
</form>
</table>

</body>
</html>