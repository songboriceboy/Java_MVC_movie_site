package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Viewer;
import mm.model.helper.BookingConfirmInfo;

public class BookingConfirmCommand extends Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int viewerID = ((Viewer)request.getSession().getAttribute("viewer_ss")).getId();
    int scheduleID;
    int nTickets;
    try {
      scheduleID = Integer.parseInt(request.getParameter("scheduleID"));
      nTickets = Integer.parseInt(request.getParameter("ntickets"));
    } catch (Exception e) {
      request.setAttribute("invalidInput", "Invalid input!");
      return "bookingSelectCinema";
    }

    int numSeatsLeft = dao.findNumSeatsLeftBySchedule(scheduleID);
    if (nTickets > numSeatsLeft) {
      request.setAttribute("selectedSID", scheduleID);
      request.setAttribute("notEnoughSeats", "It should not be larger than "+numSeatsLeft);
      return "bookingSelectCinema";
    }
    
    dao.addBooking(viewerID, scheduleID, nTickets);
    // TODO: best way to find the ID of the newly inserted row
    int bookingID = dao.findBookingIdByViewerSchedule(viewerID, scheduleID);
    BookingConfirmInfo bc = dao.findBookingConfirmById(bookingID);
    request.setAttribute("bc", bc);
    request.setAttribute("movie", bc.getMovie());
    request.setAttribute("fromOperation", "bookingConfirm");
    return "/booking.jsp";
  }

}
