<%@ include file="owner-header.jsp"%>

<table>
<tr><td width="100"><b>Movie Title:</b></td><td>${title}</td></tr>
<tr><td><b>Cinema:</b></td><td>
<form action="dispatcher?operation=showCinemaSchedule&movieid=${movieid}&title=${title}" method="post">
<select name="cinemaid">
<c:forEach var="c" items="${cinemas}">
  <option value="${c.key}" <c:if test="${cinemaid eq c.key}">selected</c:if> >${c.value}</option>
</c:forEach>
</select>
<input type="submit" value="Show Schedule">
</form>
</td></tr></table>

<form action="dispatcher?operation=addMovieTime&movieid=${movieid}&cinemaid=${cinemaid}" method="post">
<table>
<tr><td width="100"><b>Start Date:</b></td><td><input type="text" name="startdate">(YY-MM-DD or MM-DD)</td></tr>
<tr><td>            <b>End Date:  </b></td><td><input type="text" name="enddate"></td></tr>
<tr><td>            <b>Timeslot:  </b></td><td>
<select name="timeslot">
  <option value="0900">9:00am</option>
  <option value="1200">12:00pm</option>
  <option value="1500">3:00pm</option>
  <option value="1800">6:00pm</option>
</select></td></tr>
<tr><td></td><td><input type="submit" value="Add Time"></td></tr>
</table>
</form>

<h3>Screening Schedule - ${cinemaName} Cinema</h3>
<table border="1">
<tr align="center" valign="middle"><td width="50"></td><c:forEach var="d" items="${days}"><td width="80">${d}</td></c:forEach></tr>
<c:forEach var="ts" items="${timeslotArray}">
<tr align="center" valign="middle"><td height="40">${timeslotShowName[ts]}</td>
<c:forEach var="cell" items="${scheduleList[ts]}">
  <c:if test="${empty cell}"><td>&nbsp;</td></c:if>
  <c:if test="${not empty cell}"><td colspan="${cell.first}" bgcolor="#DAC8FF">${cell.second}</td></c:if>
</c:forEach></tr>
</c:forEach>
</table>

</body>
</html>