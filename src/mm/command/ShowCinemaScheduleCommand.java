package mm.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Movie;
import mm.model.Pair;
import mm.model.Schedule;
import mm.util.Util;

public class ShowCinemaScheduleCommand extends Command {
  
  private static final String[] tsA = {"0900","1200","1500","1800"};
  private static final Map<String, String> timeslotShowName = new HashMap<String,String>(){{
    put("0900","9:00am"); put("1200","12:00pm"); put("1500","3:00pm"); put("1800","6:00pm"); 
  }};

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int movieid;
    String title = request.getParameter("title");
    String movieidStr = request.getParameter("movieid");
    if (movieidStr == null) { // redirected from AddMovieCommand
      movieid = dao.findMovieIdByTitle(title);
    } else {
      movieid = Integer.parseInt(movieidStr);
    }
    request.setAttribute("movieid", movieid);
    request.setAttribute("title", title);
    
    Map<Integer,String> cinemas = dao.findCinemasIdLocationMap();
    request.setAttribute("cinemas", cinemas);
    
    String cinemaIdStr = request.getParameter("cinemaid");
    int cinemaid;
    if (cinemaIdStr == null) {
      cinemaid = cinemas.keySet().iterator().next();
    } else { // from clicking 'Show Schedule' button
      cinemaid = Integer.parseInt(cinemaIdStr);
    }
    request.setAttribute("cinemaid", cinemaid);
    request.setAttribute("cinemaName", cinemas.get(cinemaid));
    
    // For displaying the schedule table. All designed specifically for the view.
    Date today = new Date();
    Date lastDay = dao.findScheduleLastDayByCinema(cinemaid);
    List<Date> days = Util.getDaysInBetween_Inclusive_AtLeast7(today, lastDay);
    Date[] dA = new Date[days.size()];
    String[] dSA = new String[days.size()];
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); // MM/dd/yyyy HH:mm:ss
    for (int i = 0; i < days.size(); i++) {
      dA[i] = days.get(i);
      dSA[i] = fmt.format(dA[i]);
    }
    
    // Map<TIMESLOT, List<Pair<NUM_OF_CONSECUTIVE_DAYS, MOVIE_TITLE>>>
    Map<String, List<Pair<Integer,String>>> scheduleList = new HashMap<String, List<Pair<Integer,String>>>();
    for (String ts : tsA) {
      Movie[] mA = new Movie[dA.length];
      if (lastDay != null) {
        List<Schedule> ss = dao.findSchedulesByCinemaTimeslot(cinemaid, ts);
        Iterator<Schedule> iter = ss.iterator();
        if (iter.hasNext()) {
          Schedule s = iter.next();
          for (int i = 0; i < dA.length; i++) {
            Date sd = s.getDate();
            int cmp = Util.compareDay(sd, dA[i]);
            if (cmp == 0) { // sd is same as dA[i]
              mA[i] = s.getMovie();
              if (iter.hasNext())
                s = iter.next();
            } else {
              mA[i] = null; // TODO: remove Movie.dummyMovie() ?
              // cmp == 1, sd is after dA[i], empty slots before each consecutive block of schedule
              // cmp == -1, sd is before dA[i], empty slots at the tail
            }
          }
        }
      }
      List<Pair<Integer, String>> lp = shrink(mA);
      scheduleList.put(ts, lp);
    }
    request.setAttribute("days", dSA);
    request.setAttribute("scheduleList", scheduleList);
    request.setAttribute("timeslotArray", tsA);
    request.setAttribute("timeslotShowName", timeslotShowName);
    printTableForDebug(dSA, scheduleList);
    
    return "/owner-addmovietime.jsp";
  }

  private List<Pair<Integer, String>> shrink(Movie[] mA) {
    List<Pair<Integer, String>> lp = new ArrayList<Pair<Integer, String>>();
    for (int i = 0; i < mA.length; ) {
      Movie m = mA[i];
      if (m == null) { // TODO:  || m.isDummyMovie() ?
        lp.add(null); // TODO: new Pair<Integer,String>(0,"N/A") ? 
        i++;
      } else {
        Pair<Integer, String> pair = new Pair<Integer,String>(1, m.getTitle());
        while (++i < mA.length && m.equals(mA[i]))
          pair.setFirst(pair.getFirst() + 1);
        lp.add(pair);
      }
    }
    return lp;
  }

  private void printTableForDebug(String[] dSA, Map<String, List<Pair<Integer, String>>> scheduleList) {
    int cellSize = -1;
    for (String s : dSA) {
      String sp = " "+s+" "; // ' 2014-10-12 ' 12 characters
      cellSize = sp.length();
      System.out.print(sp);
    }
    System.out.println();
    
    for (String ts : tsA) {
      List<Pair<Integer, String>> lp = scheduleList.get(ts);
      for (Pair<Integer, String> p : lp) {
        if (p == null) {
          int n = cellSize - 2;
          System.out.printf(" %"+n+"s|", "N/A");
        } else {
          int n = cellSize * p.getFirst() - 2;
          System.out.printf(" %"+n+"s|", p.getSecond());
        }
      }
      System.out.println();
    }
  }

}
