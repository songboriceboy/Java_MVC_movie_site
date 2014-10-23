package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMovieCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String title = request.getParameter("title");
    String poster = request.getParameter("poster");
    String genre = request.getParameter("genre");
    String synopsis = request.getParameter("synopsis");
    String director = request.getParameter("director");
    String actors = request.getParameter("actors");
    dao.addMovie(title,poster,genre,synopsis,director,actors);
    request.setAttribute("movieAdded", title);
    return "showCinemaSchedule";
  }
}
