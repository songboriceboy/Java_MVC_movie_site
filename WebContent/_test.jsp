<%@ include file="owner-header.jsp"%>

<c:forEach var="pa" items="${pairs}"><p>${pa.first} | ${pa.second}</p></c:forEach>

<c:if test="${fromOperation eq 'bookingSelectCinema'}"><p>yeah</p></c:if>

</body>
</html>