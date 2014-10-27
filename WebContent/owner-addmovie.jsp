<%@ include file="owner-header.jsp"%>

<form action="dispatcher?operation=addMovie">
<table>
<tr><td><b>Movie Title:</b></td><td><input type="text" name="title"></td></tr>
<tr><td><b>Poster:</b></td><td><input type="text" name="poster"></td></tr>
<tr><td><b>Main Actors:</b></td><td><input type="text" name="actors"></td></tr>
<tr><td><b>Genre:</b></td><td>
<select name="genre">
 <option value="Science-Fiction">Science-Fiction</option>
 <option value="Fantasy">Fantasy</option>
 <option value="Dramma">Dramma</option>
 <option value="Comedy">Comedy</option>
 <option value="Thriller">Thriller</option>
 <option value="Action">Action</option>
</select>
</td></tr>
<tr><td><b>Director:</b></td><td><input type="text" name="director"></td></tr>
<tr><td><b>Short Synopsis:</b><br/>(about 100 words)</td><td><textarea name="synopsis" rows="5" cols="30"></textarea></td></tr>
<tr><td></td><td><input type="submit" value="Add Movie"></td></tr>
</table>
</form>

</body>
</html>