package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingConfirmCommand extends Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("fromOperation", "bookingConfirm");
    return "/booking.jsp";
  }

}
