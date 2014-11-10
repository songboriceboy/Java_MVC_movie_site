package mm.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.BookingPerSchedule;
import mm.model.Cinema;
import mm.model.Movie;
import mm.model.Schedule;
import mm.util.Constants;

public class BookingSelectCinemaCommand extends Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int movieid = Integer.parseInt(request.getParameter("movieid"));
    Movie m = dao.findMovie(movieid);
    request.setAttribute("movie", m);
    
    Map<Integer,String> cinemas = dao.findCinemasIdLocationMapWithMovieSchedule(movieid);
    request.setAttribute("cinemas", cinemas);
    
    String cinemaIdStr = request.getParameter("cinemaid");
    int cinemaid;
    if (cinemaIdStr == null)
      cinemaid = cinemas.keySet().iterator().next();
    else
      cinemaid = Integer.parseInt(cinemaIdStr);
    request.setAttribute("cinemaid", cinemaid);
    Cinema c = dao.findCinema(cinemaid);
    request.setAttribute("unitPrice", c.getUnitPrice());
    
    List<Schedule> schedules = dao.findScheduleByCinemaMovie(cinemaid, movieid);
    BookingPerSchedule[] bsa = new BookingPerSchedule[schedules.size()];
    int i = 0;
    SimpleDateFormat fmt = new SimpleDateFormat("d MMM yyyy");
    for (Schedule s : schedules) {
      bsa[i] = new BookingPerSchedule();
      bsa[i].setScheduleID(s.getId());
      String str = fmt.format(s.getDate()) + ", " + Constants.tsMap.get(s.getTimeslot());
      int n = c.getSeatCapacity() - s.getNumTickets();
      if (n > 0) {
        str += " ("+n+" seats left)";
        bsa[i].setSoldOut(false);
      } else {
        str += " (Sold out)";
        bsa[i].setSoldOut(true);
      }
      bsa[i].setStrRep(str);
      i++;
    }
    request.setAttribute("bookingStatusArray", bsa);

    request.setAttribute("fromOperation", "bookingSelectCinema");
    return "/booking.jsp";
  }

}
