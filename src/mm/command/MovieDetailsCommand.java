package mm.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Movie;
import mm.model.Review;

public class MovieDetailsCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("movieid"));
    Movie m = dao.findMovieDetails(id);
    request.setAttribute("m", m);
    String status = m.getStatus();
    if (status.equals("Now Showing")) {
      request.setAttribute("showBooking", true);
    } 
    if (status.equals("Now Showing") || status.equals("No future showing time scheduled.")) {
      List<Review> reviews = dao.findReviewsByMovie(id);
      request.setAttribute("reviews", reviews);
      request.setAttribute("showReviews", true);
    }
    return "/moviedetails.jsp";
  }
}
