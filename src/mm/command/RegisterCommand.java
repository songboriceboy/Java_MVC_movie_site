package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO: unique username, form validation.
    // TODO: email user and activation.
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    dao.addViewer(username, password, email);
    return "/login.jsp";
  }
}
