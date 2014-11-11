<%@ include file="header.jsp"%>

<table width="700"><tr valign="top">
<td width="280"><img src="${movie.poster}" height="400" width="266"></td>
<td><table>
<tr><td> <font size="6"><b>${movie.title}</b></font> </td></tr>

<c:choose>

<c:when test="${fromOperation eq 'bookingSelectCinema'}">
<tr><td>Booking Details:</td></tr>
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
<input type="radio" name="scheduleID" value="${b.scheduleID}"
 <c:if test="${b.scheduleID eq selectedSID}">checked</c:if>
 <c:if test="${b.isSoldOut}">disabled</c:if> > ${b.strRep} <br/>
</c:forEach>
</td></tr>
<tr><td><b>Unit Price:</b></td><td>$${unitPrice}</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td><input name="ntickets" type="text" size="3" value="${param.ntickets}">${notEnoughSeats}</td></tr>
<tr><td><b>Credit Card number:</b></td><td><input name="ccnumber" type="text" size="10" value="${param.ccnumber}"></td></tr>
<tr><td></td><td><input type="submit" value="Book!">${invalidInput}</td></tr>
</table>
</form>
</td></tr>
</c:when>

<c:when test="${fromOperation eq 'bookingConfirm'}">
<tr><td>Booking successful!</td></tr>
<tr><td>
<table>
<tr><td width="110"><b>Booking ID:</b></td><td>${bc.bookingID}</td></tr>
<tr><td><b>Cinema:</b></td><td>${bc.cinema}</td></tr>
<tr><td><b>Date:</b></td><td>${bc.date}</td></tr>
<tr><td><b>Time:</b></td><td>${bc.timeslot}</td></tr>
<tr><td><b>Unit Price:</b></td><td>$${bc.unitPrice}</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td>${bc.numTickets}</td></tr>
<tr><td><b>Total Price:</b></td><td>$${bc.totalPrice}</td></tr>
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