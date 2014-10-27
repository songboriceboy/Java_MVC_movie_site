package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.Viewer;

public class UpdateDetailsCommand extends Command {

  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Viewer viewer = (Viewer) request.getSession().getAttribute("viewer_ss");
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    String nickname = request.getParameter("nickname");
    String email = request.getParameter("email");
    
    // TODO: same check and empty check.
//    if (!firstname.equals(viewer.getFirstname()) || !lastname.equals(viewer.getLastname()) || 
//        !nickname.equals(viewer.getNickname()) || !email.equals(viewer.getEmail())) {
      dao.updateViewerDetails(viewer.getId(), firstname, lastname, nickname, email);
      viewer.setFirstname(firstname);
      viewer.setLastname(lastname);
      viewer.setNickname(nickname);
      viewer.setEmail(email);
      request.setAttribute("updatedone", true);
//    }
    return "/mydetails.jsp";
  }
}
