package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Owner;
import mm.model.Viewer;

public class LoginCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    Viewer viewer = dao.findViewer(username, password);
    Owner owner = dao.findOwner(username, password);
    
    if (viewer != null) {
      request.getSession().setAttribute("viewer_ss", viewer);
      return "home";
    } else if (owner != null) {
      request.getSession().setAttribute("owner_ss", owner);
      return "/owner-home.jsp";
    } else {
      request.setAttribute("loginError", true);
      return "/login.jsp";
    }
    // TODO: Better way to distinguish viewer and owner when login.
  }
}
