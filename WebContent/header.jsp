<%@ include file="header0.jsp"%>
<h1>Welcome to MiaowuMovie!</h1>
<table>
<tr><td>You are logged in as: ${viewer_ss.username}. </td>
<td>|</td>
<td><a href="dispatcher?operation=home">Home</a></td>
<td>|</td>
<td><a href="dispatcher?operation=search">Search</a></td>
<td>|</td>
<td><a href="dispatcher?operation=myaccount">My Account</a></td>
<td>|</td>
<td><a href="dispatcher?operation=logout">Logout</a></td>
<td>|</td>
</table>
<br/>
