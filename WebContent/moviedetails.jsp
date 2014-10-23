<%@ include file="header.jsp"%>

<table width="700"><tr valign="top">
<td width="280"><img src="${m.poster}" height="400" width="266"></td>
<td><table>
<tr><td> <font size="6"><b>${m.title}</b></font></td></tr>
<tr><td><p>${m.synopsis}</p></td></tr>
<tr><td><table>
<tr><td><b>Director:</b></td><td>${m.director}</td></tr>
<tr><td><b>Main Actors:</b></td><td>${m.actors}</td></tr>
<tr><td><b>Genre:</b></td><td>${m.genre}</td></tr>
<tr><td><b>Rating:</b></td><td>
  <c:choose>
    <c:when test="${m.numReviews eq 0}">
      No rating yet.
    </c:when>
    <c:otherwise>
      <strong>${m.rating}</strong> by ${m.numReviews} users.
    </c:otherwise>
  </c:choose>
</td></tr>
<tr><td><b>Status:</b></td><td>${m.status}</td></tr>
<c:if test="${showBooking}">
<tr><td><b>Tickets:</b></td><td><a href="dispatcher?operation=booking&movieid=${m.id}">Book Now!</a></td></tr>
</c:if>
</table></td></tr>
</table></td>
</tr></table>

<c:if test="${showReviews}">
<table  width="700">
<tr><td><h2 style="font-family: verdana; color: orange;">Reviews:</h2></td></tr>
<c:choose>
  <c:when test="${empty reviews}">
    <tr><td>No review yet.</td></tr>
  </c:when>
  <c:otherwise>
<c:forEach var="r" items="${reviews}">
<tr><td><table>
<tr><td><b>${r.title}</b><br/> Rating <strong>${r.rating}</strong> | by ${r.username} at ${r.timeAdded} </td></tr>
<tr><td><p>${r.comment}</p></td></tr>
</table></td></tr>
</c:forEach>
  </c:otherwise>
</c:choose>
</table>

<form action="dispatcher?operation=addReview" method="post">
<table width="700">
<tr><td><h2 style="font-family: verdana; color: orange;">Post My Review:</h2></td></tr>
<tr><td><table>
<tr valign="top"><td><b>Title:</b></td><td><input name="title" type="text" size="70"></td></tr>
<tr valign="top"><td><b>Rating:</b></td><td><input name="rating" type="text" size="4" maxlength="1">(1 to 5)</td></tr>
<tr valign="top"><td><b>Comment:</b></td><td><textarea name="comment" rows="7" cols="80">Add your comment here!</textarea></td></tr>
</table></td></tr>
<tr><td><input type="submit" value="Post Review!"></td></tr>
</table>
</form>
</c:if>

</body>
</html>