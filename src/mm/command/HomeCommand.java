package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Owner;
import mm.model.Viewer;

public class HomeCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    return "/home.jsp";
  }
}
