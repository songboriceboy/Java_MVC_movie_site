<%@ include file="owner-header.jsp"%>

<c:forEach var="pa" items="${pairs}"><p>${pa.first} | ${pa.second}</p></c:forEach>

<c:if test="${3 eq 3}"><p>yeah3</p></c:if>

</body>
</html>