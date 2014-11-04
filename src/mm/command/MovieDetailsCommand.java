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
    List<Review> reviews = dao.findReviewsByMovie(id);
    request.setAttribute("m", m);
    request.setAttribute("reviews", reviews);
    return "/moviedetails.jsp";
  }
}
