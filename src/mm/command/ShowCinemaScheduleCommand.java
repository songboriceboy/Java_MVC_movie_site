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
import mm.model.Schedule;
import mm.model.helper.Pair;
import mm.util.Constants;
import mm.util.Util;

public class ShowCinemaScheduleCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String title = request.getParameter("title"); // could be from AddMovie
    request.setAttribute("title", title);

    int movieid;
    String movieidStr = request.getParameter("movieid");
    if (movieidStr == null) { // redirected from AddMovieCommand
      movieid = dao.findMovieIdByTitle(title); //TODO: better?
    } else { // from clicking 'Show Schedule' or 'Add Time' button (showCinemaSchedule / addMovieTime)
      movieid = Integer.parseInt(movieidStr);
    }
    request.setAttribute("movieid", movieid);
    
    Map<Integer,String> cinemas = dao.findCinemasIdLocationMap();
    request.setAttribute("cinemas", cinemas);
    
    String cinemaIdStr = request.getParameter("cinemaid");
    int cinemaid;
    if (cinemaIdStr == null) { // redirected from AddMovieCommand
      cinemaid = cinemas.keySet().iterator().next();
    } else { // from clicking 'Show Schedule' or 'Add Time' button (showCinemaSchedule / addMovieTime)
      cinemaid = Integer.parseInt(cinemaIdStr);
    }
    request.setAttribute("cinemaid", cinemaid);
    request.setAttribute("cinemaName", cinemas.get(cinemaid));
    
    /* Below is for displaying the 'Screening Schedule' table. All designed specifically for the view. */
    
    // produce a list of dates from today, at least 7 days if lastDay is within 7 days.
    Date today = new Date();
    Date lastDay = dao.findScheduleLastDayByCinema(cinemaid);
    List<Date> days = Util.getDaysInBetween_Inclusive(today, lastDay, 7);
    Date[] dA = new Date[days.size()];
    String[] dSA = new String[days.size()];
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); // MM/dd/yyyy HH:mm:ss
    for (int i = 0; i < days.size(); i++) {
      dA[i] = days.get(i);
      dSA[i] = fmt.format(dA[i]);
    }
    
    // Map<TIMESLOT, List<Pair<NUM_OF_CONSECUTIVE_DAYS, MOVIE_TITLE>>>
    Map<String, List<Pair<Integer,String>>> scheduleList = new HashMap<String, List<Pair<Integer,String>>>();
    for (Pair<String,String> tsp : Constants.tspA) {
      String ts = tsp.getFirst();
      Movie[] mA = new Movie[dA.length];
      if (lastDay != null) {
        List<Schedule> ss = dao.findSchedulesByCinemaTimeslotFromToday(cinemaid, ts);
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
    request.setAttribute("timeslotPairArray", Constants.tspA);
    printTableForDebug(dSA, scheduleList);
    
    return "/owner-addmovietime.jsp";
  }

  private List<Pair<Integer, String>> shrink(Movie[] mA) {
    List<Pair<Integer, String>> lp = new ArrayList<Pair<Integer, String>>();
    for (int i = 0; i < mA.length; ) {
      Movie m = mA[i];
      if (m == null) {
        lp.add(null); // java List supports adding null element.
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
    
    for (Pair<String,String> tsp : Constants.tspA) {
      String ts = tsp.getFirst();
      List<Pair<Integer, String>> lp = scheduleList.get(ts);
      for (Pair<Integer, String> p : lp) {
        if (p == null) {
          int n = cellSize - 2;
          System.out.printf(" %"+n+"s|", "N/A");
        } else {
          int n = cellSize * p.getFirst() - 2;
          String title = p.getSecond();
          if (title.length() > n) title = title.substring(0, n);
          System.out.printf(" %"+n+"s|", title);
        }
      }
      System.out.println();
    }
  }

}
