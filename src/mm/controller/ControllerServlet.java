package mm.controller;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mm.command.*;
import mm.db.dao.DataAccessObject;

@WebServlet(urlPatterns="/dispatcher",displayName="miaowuController")
public class ControllerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(ControllerServlet.class.getName());
  private Map<String,Command> commands;
  
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    logger.info("init starts.");
    commands = new HashMap<String,Command>();
    // TODO: make these command strings into a enum, share with jsp. Package mm.shared.model and mm.shared.common
    commands.put("login", new LoginCommand());
    commands.put("logout", new LogoutCommand());
    commands.put("register", new RegisterCommand());
    commands.put("updateDetails", new UpdateDetailsCommand());
    commands.put("home", new HomeCommand());
    commands.put("search", new SearchCommand());
    commands.put("movieDetails", new MovieDetailsCommand());
    commands.put("addReview", new AddReviewCommand());
    commands.put("addCinema", new AddCinemaCommand());
    commands.put("addMovie", new AddMovieCommand());
    commands.put("showCinemaSchedule", new ShowCinemaScheduleCommand());
    commands.put("addMovieTime", new AddMovieTimeCommand());
    commands.put("bookingSelectCinema", new BookingSelectCinemaCommand());
    commands.put("bookingConfirm", new BookingConfirmCommand());
    commands.put("mybookings", new MyBookingsCommand());
    commands.put("test", new TestCommand());
    logger.info("init finishes.");
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    String op = request.getParameter("operation");
    Command cmd = (Command) commands.get(op);
    String next;
    if (cmd != null) {
      logger.info("Executing command: " + cmd.getClass().getName());
      next = cmd.execute(request, response);
      while (next.indexOf('.') < 0) { // not jsp file
        cmd = (Command) commands.get(next);
        next = cmd.execute(request, response);
      }
    } else {
      request.setAttribute("errorMsg", "operation "+op+" cannot be resolved.");
      next = "/error.jsp";
    }
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
    dispatcher.forward(request, response);
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
