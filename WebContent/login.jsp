<%@ include file="header0.jsp"%>
<h1>MiaowuMovie Login</h1>
<form action="dispatcher?operation=login" method="post">
<table>
<tr><td>Username:</td><td><input name="username" type="text"></td></tr>
<tr><td>Password:</td><td><input name="password" type="password"></td></tr>
<tr><td><input value="Login!" type="submit"></td><td><c:if test="${loginError}"><font color="red">Login failed. Try again.</font></c:if></td></tr>
</table>
</form>
Not a member? <a href="register.jsp">Join Now!</a>
</body>
</html>