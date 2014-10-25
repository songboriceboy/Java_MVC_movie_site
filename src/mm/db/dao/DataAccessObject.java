package mm.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.NamingException;

import mm.db.jdbc.QuickDBConnectionFactory;
import mm.model.Owner;
import mm.model.Viewer;

public class DataAccessObject {
  private static Logger logger = Logger.getLogger(DataAccessObject.class.getName());
  private Connection con;

  public DataAccessObject() {
    try {
      con = QuickDBConnectionFactory.getConnection();
    } catch (NamingException | SQLException e) {
      e.printStackTrace();
    }
  }

  public Viewer findViewer(String username, String password) {
    try {
      PreparedStatement stmt = con.prepareStatement("SELECT * FROM viewers WHERE username = ? AND password = ?");
      stmt.setString(1, username);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        logger.info("Found viewer: " + username);
        int id = rs.getInt("id");
        String nickname = rs.getString("nickname");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String email = rs.getString("email");
        boolean activated = rs.getBoolean("activated");
        return new Viewer(id, username, password, nickname, firstname, lastname, email, activated);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Owner findOwner(String username, String password) {
    try {
      PreparedStatement stmt = con.prepareStatement("SELECT * FROM owners WHERE username = ? AND password = ?");
      stmt.setString(1, username);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        logger.info("Found owner: " + username);
        int id = rs.getInt("id");
        return new Owner(id, username, password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
