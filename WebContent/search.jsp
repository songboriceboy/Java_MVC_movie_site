<%@ include file="header.jsp"%>

<form action="dispatcher?operation=search" method="post">
<input name="keyword" type="text"> 
<input name="title" type="checkbox" checked> Title
<input name="genre" type="checkbox" checked> Genre
<input value="Search!" type="submit"><c:if test="${!empty errorMsg}"><font color="red">${errorMsg}</font></c:if>
</form>
<h2>Search Results:</h2>
<table>
<tr><th width="150">Poster</th><th width="150">Title</th><th width="150">Genre</th><th width="150">Main Actors</th><th width="100">Rating</th><th width="120">Status</th><th width="100">Details</th></tr>
<c:forEach var="m" items="${SearchResult}">
<tr align="center"><td><img src="${m.poster}" height="150" width="100"></td><td><b>${m.title}</b></td><td>${m.genre}</td><td>${m.actors}</td>
<td><c:choose>
      <c:when test="${m.numReviews eq 0}">
        No review yet.
      </c:when>
      <c:otherwise>
        ${m.rating} (${m.numReviews} Reviews)
      </c:otherwise>
    </c:choose></td>
<td>${m.status}</td>
<td><a href="dispatcher?operation=movieDetails&movieid=${m.id}">Details</a></td></tr>
</c:forEach>
</table>
</body>
</html>