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

  public void addViewer(String username, String password, String email) {
    try {
      PreparedStatement stmt = con.prepareStatement(
          "INSERT INTO viewers (username, password, email) VALUES (?,?,?)");
      stmt.setString(1, username);
      stmt.setString(2, password);
      stmt.setString(3, email);
      if (stmt.executeUpdate() == 1)
        logger.info("Added viewer: " + username);
      else
        warnZeroRowAffected();
    } catch (SQLException e) {
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
//        if (nickname == null) nickname = "";
//        if (firstname == null) firstname = "";
//        if (lastname == null) lastname = "";
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

  public void updateViewerDetails(int id, String firstname, String lastname, String nickname, String email) {
    try {
      PreparedStatement stmt = con.prepareStatement(
          "UPDATE viewers SET firstname=?, lastname=?, nickname=?, email=? WHERE id=?");
      stmt.setString(1, firstname);
      stmt.setString(2, lastname);
      stmt.setString(3, nickname);
      stmt.setString(4, email);
      stmt.setInt(5, id);
      if (stmt.executeUpdate() == 1)
        logger.info("Updated viewer: ID " + id);
      else
        warnZeroRowAffected();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addCinema(String location, short seatCap, short unitPrice) {
    try {
      PreparedStatement stmt = con.prepareStatement(
          "INSERT INTO cinemas VALUES (default,?,?,?)");
      stmt.setString(1, location);
      stmt.setShort(2, seatCap);
      stmt.setShort(3, unitPrice);
      if (stmt.executeUpdate() == 1)
        logger.info("Added cinema: " + location);
      else
        warnZeroRowAffected();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void warnZeroRowAffected() {
    logger.warning("0 row affected.");
  }
  
}
