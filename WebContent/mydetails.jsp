<%@ include file="header.jsp"%>

<h2>My Details:</h2>
<form action="dispatcher?operation=updateDetails" method="post">
<table>
<tr><td>User name:</td><td>${viewer_ss.username}</td></tr>
<tr><td>First name:</td><td><input type="text" name="firstname" value="${viewer_ss.firstname}"></td></tr>
<tr><td>Last name:</td><td><input type="text" name="lastname" value="${viewer_ss.lastname}"></td></tr>
<tr><td>Nick name:</td><td><input type="text" name="nickname" value="${viewer_ss.nickname}"></td></tr>
<tr><td>Email:</td><td><input type="text" name="email" value="${viewer_ss.email}" size="40"></td></tr>
<tr><td></td><td><input type="submit" value="Update!"></td></tr>
<c:if test="${updatedone}"><tr><td></td><td>Update Successful!</td></tr></c:if>
</table>
</form>

</body>
</html>