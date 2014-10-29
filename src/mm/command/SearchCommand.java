package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String keyword = request.getParameter("keyword");
    boolean byTitle = request.getParameter("title") != null; 
    boolean byGenre = request.getParameter("genre") != null; 
    
//    if (!byTitle && !byGenre) {
//      request.setAttribute("errorMsg", "Please select at least one search criteria.");
//      return "/search.jsp";
//    }

    request.setAttribute("SearchResult", dao.searchMovie(keyword, byTitle, byGenre));;
    if (request.getSession().getAttribute("viewer_ss") != null) { // viewer's session
      return "/search.jsp";
    } else { // owner's session
      return "/owner-search.jsp";
    }
    // TODO: better way to distinguish viewer / owner's session; same as in error.jsp
  }
}
