<% if (request.getSession().getAttribute("owner_ss") != null) { %>
<%@ include file="owner-header.jsp"%>
<% } else { %>
<%@ include file="header.jsp"%>
<% } %>

<h3>ERROR!!<c:if test="${!empty errorMsg}"> ${errorMsg}</c:if></h3>
<img src="images/seppuku.jpg" alt="Image from arsenelumpen.wordpress.com" height="500"/>

</body>
</html>