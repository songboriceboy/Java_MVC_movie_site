package mm.command;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Movie;
import mm.model.Owner;
import mm.model.Schedule;
import mm.model.Viewer;

public class HomeCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Movie> ns = dao.listNowShowingMovies();
    List<Movie> cs = dao.listCoomingSoonMovies();
    request.setAttribute("NowShowingList", ns);
    request.setAttribute("ComingSoonList", cs);
    return "/home.jsp";
  }
}
