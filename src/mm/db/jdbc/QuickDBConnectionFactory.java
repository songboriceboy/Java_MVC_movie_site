package mm.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mm.controller.ControllerServlet;

public class QuickDBConnectionFactory {
  private static Logger logger = Logger.getLogger(QuickDBConnectionFactory.class.getName());
  private static DataSource ds;

  public static Connection getConnection() throws NamingException, SQLException {
    if (ds == null)
      ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/DerbyDB");
    Connection con = ds.getConnection();
    logger.info("found connection: " + con);
    return con;
  }
}
