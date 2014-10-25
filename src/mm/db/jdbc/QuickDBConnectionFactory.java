package mm.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QuickDBConnectionFactory {
  private static DataSource ds;

  public static Connection getConnection() throws NamingException, SQLException {
    if (ds == null)
      ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/DerbyDB");
    return ds.getConnection();
  }
}
