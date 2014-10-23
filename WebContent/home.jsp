<%@ include file="header.jsp"%>
<h1 style="font-family: verdana; color: red;"> Now Showing: </h1>
<table><tr>
<c:forEach var="m" items="${NowShowingList}">
<td width="210"> <a href="dispatcher?operation=movieDetails&movieid=${m.id}"><img src="${m.poster}" height="300" width="200"></a> <p><b>${m.title}</b></p>
    <c:choose>
      <c:when test="${m.numReviews eq 0}">
        No review yet.
      </c:when>
      <c:otherwise>
        Rating: ${m.rating} (${m.numReviews} Reviews)
      </c:otherwise>
    </c:choose>
</td>
</c:forEach>
</tr></table>
<h2 style="font-family: times; color: blue;"> Coming Soon:</h2>
<table><tr>
<c:forEach var="m" items="${ComingSoonList}">
<td width="210"> <a href="dispatcher?operation=movieDetails&movieid=${m.id}"><img src="${m.poster}" height="300" width="200"></a> <p><b>${m.title}</b></p>${m.status}</td>
</c:forEach>
</tr></table>
</body>
</html>