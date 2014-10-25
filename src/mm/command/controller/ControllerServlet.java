package mm.command.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mm.command.Command;
import mm.command.HomeCommand;
import mm.command.LoginCommand;
import mm.command.LogoutCommand;

@WebServlet(urlPatterns="/dispatcher",displayName="miaowuController")
public class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private Map<String,Command> commands;
  
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    commands = new HashMap<String,Command>();
    commands.put("login", new LoginCommand());
    commands.put("logout", new LogoutCommand());
    commands.put("home", new HomeCommand());
//    commands.put("PAGE_NOT_FOUND", new ErrorCommand());
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    Command cmd = resolveCommand(request);
    String next = cmd.execute(request, response);
    if (next.indexOf('.') < 0) {
      cmd = (Command) commands.get(next);
      next = cmd.execute(request, response);
    }   
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
    dispatcher.forward(request, response);
  }
  
  private Command resolveCommand(HttpServletRequest request) {
    Command cmd = (Command) commands.get(request.getParameter("operation"));
    if (cmd == null) {
      cmd = (Command) commands.get("PAGE_NOT_FOUND");
    }
    return cmd;
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    processRequest(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    processRequest(request, response);
  }
  
  public String getServletInfo() {
    return "This servlet implements a command pattern for MiaowuMovie application";
  }
}
