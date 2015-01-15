package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Viewer;

public class AddReviewCommand extends Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int viewerID = ((Viewer)request.getSession().getAttribute("viewer_ss")).getId();
    int movieID = Integer.parseInt(request.getParameter("movieid"));
    String title = request.getParameter("title");
    float rating = Float.parseFloat(request.getParameter("rating"));
    String comment = request.getParameter("comment");
    dao.addReview(viewerID,movieID,title,rating,comment);
    return "movieDetails";
  }

}
