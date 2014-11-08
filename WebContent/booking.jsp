<%@ include file="header.jsp"%>

<table width="700"><tr valign="top">
<td width="280"><img src="${moviePoster}" height="400" width="266"></td>
<td><table>
<tr><td> <font size="6"><b>${movieTitle}</b></font> </td></tr>
<tr><td>Booking Details:</td></tr>

<c:choose>

<c:when test="${fromOperation eq 'bookingSelectCinema'}">
<tr><td><table>
<tr><td width="110"><b>Cinema:</b></td><td>
<form action="dispatcher?operation=bookingSelectCinema&movieid=${param.movieid}" method="post">
<select name="cinemaid">
<c:forEach var="c" items="${cinemas}">
  <option value="${c.key}" <c:if test="${cinemaid eq c.key}">selected</c:if> >${c.value}</option>
</c:forEach>
</select>
<input type="submit" value="Change Cinema">
</form>
</td></tr>
</table></td></tr>

<tr><td>
<form action="dispatcher?operation=bookingConfirm&movieid=${param.movieid}&cinemaid=${cinemaid}" method="post">
<table>
<tr><td width="110"><b>Date & Time:</b></td><td>
<c:forEach var="b" items="${bookingStatusArray}">
<input type="radio" name="scheduleID" value="${b.scheduleID}" <c:if test="${b.soldOut}">disabled</c:if> > ${b.strRep} <br/>
</c:forEach>
<!--  
<input type="radio" name="datetime" value="20141001,0900"> Oct 1 2014, 09:00 (5 seats remaining) <br/>
<input type="radio" name="datetime" value="20141001,0900"> Oct 1 2014, 09:00 (5 seats remaining) <br/>
<input type="radio" name="datetime" value="20141001,0900" disabled> Oct 1 2014, 09:00 (Sold out) <br/>
-->
</td></tr>
<tr><td><b>Unit Price:</b></td><td>$${unitPrice}</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td><input name="ntickets" type="text" size="3"></td></tr>
<tr><td><b>Credit Card number:</b></td><td><input name="ccnumber" type="text" size="10"></td></tr>
<tr><td></td><td><input type="submit" value="Book!"></td></tr>
</table>
</form>
</td></tr>
</c:when>

<c:when test="${fromOperation eq 'bookingConfirm'}">
<tr><td>Booking successful!</td></tr>
<tr><td>
<table>
<tr><td width="110"><b>Booking ID:</b></td><td>123123</td></tr>
<tr><td><b>Cinema:</b></td><td>Bondi</td></tr>
<tr><td><b>Date:</b></td><td>Oct 20</td></tr>
<tr><td><b>Time:</b></td><td>9:00am</td></tr>
<tr><td><b>Unit Price:</b></td><td>$19</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td>2</td></tr>
<tr><td><b>Total Price:</b></td><td>$38</td></tr>
</table>
</td></tr>
<tr><td><a href="dispatcher?operation=mybookings">View my bookings</a></td></tr>
</c:when>

</c:choose>

<tr><td><a href="dispatcher?operation=movieDetails&movieid=${param.movieid}">Back to movie details</a></td></tr>
</table></td></tr>
</table>

</body>
</html>