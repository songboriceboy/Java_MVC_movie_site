<%@ include file="owner-header.jsp"%>

<c:forEach var="pa" items="${pairs}"><p>${pa.first} | ${pa.second}</p></c:forEach>

</body>
</html>