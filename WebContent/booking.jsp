<%@ include file="header.jsp"%>

<table width="700"><tr valign="top">
<td width="280"><img alt="zz" src="posters/edge of tomorrow.jpg" height="400" width="266"></td>
<td><table>
<tr><td> <font size="6"><b>Edge of Tomorrow</b></font> </td></tr>
<tr><td>Booking Details:</td></tr>
<tr><td>Booking successful! Your booking ID: <b>123123</b></td></tr>

<tr><td><table>
<tr><td width="110"><b>Cinema:</b></td><td>
<form action="dispatcher?operation=selectCinema" method="post">
<select name="cinemaId">
  <option value="1">Volvo</option>
  <option value="4">Saab</option>
  <option value="12">Opel</option>
  <option value="3" selected>Audi</option>
</select>
<input type="submit" value="Select Cinema">
</form>
</td></tr>
</table></td></tr>

<tr><td>
<form action="dispatcher?operation=selectCinemaDateTimeTickets" method="post">
<input type="hidden" name="cinemaId" value="1">
<table>
<tr><td width="110"><b>Date & Time:</b></td><td>
<input type="radio" name="datetime" value="20141001,0900"> Oct 1 2014, 09:00 (5 seats remaining) <br/>
<input type="radio" name="datetime" value="20141001,0900"> Oct 1 2014, 09:00 (5 seats remaining) <br/>
Sold out.
</td></tr>
<tr><td><b>Unit Price:</b></td><td>$19</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td><input name="ntickets" type="text" size="3"></td></tr>
<tr><td><a href="dispatcher?operation=display&movieid=1">Cancel</a></td><td><input type="submit" value="Next"></td></tr>
</table>
</form>
</td></tr>

<tr><td>
<form action="dispatcher?operation=confirmBooking" method="post">
<table>
<tr><td width="110"><b>Cinema:</b></td><td>George St</td></tr>
<tr><td><b>Date:</b></td><td>Oct 20</td></tr>
<tr><td><b>Time:</b></td><td>9:00am</td></tr>
<tr><td><b>Unit Price:</b></td><td>$19</td></tr>
<tr><td><b>NO. of Tickets:</b></td><td>2</td></tr>
<tr><td><b>Total Price:</b></td><td>$38</td></tr>
<tr><td><b>Credit Card number:</b></td><td><input name="ccnumber" type="text" size="10"></td></tr>
<tr><td><a href="dispatcher?operation=display&movieid=1">Cancel</a></td><td><input type="submit" value="Confirm!"></td></tr>
</table>
</form>
</td></tr>

</table></td></tr>
</table>

</body>
</html>