package mm.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Viewer;
import mm.model.helper.BookingInfo;

public class MyBookingsCommand extends Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int viewerID = ((Viewer)request.getSession().getAttribute("viewer_ss")).getId();
    List<BookingInfo> lb = dao.findBookingInfosByViewer(viewerID);
    request.setAttribute("bookings", lb);
    return "/mybookings.jsp";
  }

}
