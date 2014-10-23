package mm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.db.dao.DataAccessObject;

public abstract class Command {
  protected static DataAccessObject dao = new DataAccessObject();
  
  public Command() {
//    if (dao == null) dao = new DataAccessObject();
  }

//  protected DataAccessObject getDAO() {
//    return dao;
//  }

  public abstract String execute(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException;
}
