<%@ include file="header0.jsp"%>
<h1>Welcome to MiaowuMovie Owner's Portal!</h1>
<table>
<tr><td>You are logged in as: ${owner_ss.username}. </td>
<td>|</td>
<td><a href="owner-addcinema.jsp">Add Cinema</a></td>
<td>|</td>
<td><a href="dispatcher?operation=search">Add Movie</a></td>
<td>|</td>
<td><a href="dispatcher?operation=addMovieTime">Add Movie Time</a></td>
<td>|</td>
<td><a href="dispatcher?operation=logout">Logout</a></td>
<td>|</td>
</table>
<br/>
