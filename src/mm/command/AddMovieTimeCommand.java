package mm.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMovieTimeCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Date sd, ed;
    try {
      String sdStr = request.getParameter("startdate");
      String edStr = request.getParameter("enddate");
      String year = Calendar.getInstance().get(Calendar.YEAR) + "-";
      if (sdStr.matches("\\d\\d\\-\\d\\d")) sdStr = year  + sdStr;
      if (edStr.matches("\\d\\d\\-\\d\\d")) edStr = year  + edStr;
      SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
      sd = fmt.parse(sdStr);
      ed = fmt.parse(edStr);
    } catch (Exception e) {
      request.setAttribute("addMovieTimeError", "Date format error.");
      return "showCinemaSchedule";
    }
    int movieid = Integer.parseInt(request.getParameter("movieid"));
    int cinemaid = Integer.parseInt(request.getParameter("cinemaid"));
    String ts = request.getParameter("timeslot");
    
    if (dao.addMovieTime(movieid, cinemaid, ts, sd, ed)) {
      request.setAttribute("addMovieTimeSuccess", "Adding movie time succeeded!");
    } else {      
      request.setAttribute("addMovieTimeError", "Adding movie time failed - time collision.");
    }
    return "showCinemaSchedule";
  }
}
