package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCinemaCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO: unique location;
    // TODO: check if DB operation succeeded.
    String location = request.getParameter("location");
    short seatCap = Short.parseShort(request.getParameter("capacity"));
    short unitPrice = Short.parseShort(request.getParameter("ticketprice"));
    dao.addCinema(location, seatCap, unitPrice);
    request.setAttribute("cinemaAdded", location);
    return "/owner-home.jsp";
  }
}
