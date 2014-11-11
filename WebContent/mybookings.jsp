<%@ include file="header.jsp"%>

<h2>My Bookings:</h2>
<table>
<tr><th width="100">Booking ID</th><th>Movie</th><th>Cinema</th><th>Date</th><th width="60">Time</th><th>No. of Tickets</th><th>Unit Price</th><th>Total Price</th></tr>
<c:forEach var="bi" items="${bookings}">
<tr align="center"><td>${bi.bookingID}</td><td><a href="dispatcher?operation=movieDetails&movieid=${bi.movie.id}">${bi.movie.title}</a>
</td><td>${bi.cinema}</td><td>${bi.date}</td><td>${bi.timeslot}</td><td>${bi.numTickets}</td><td>$${bi.unitPrice}</td><td>$${bi.totalPrice}</td></tr>
</c:forEach>
</table>

</body>
</html>