package mm.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

public class DBConnectionFactory extends AbstractJndiLocator {
  private DataSource ds;

  public DBConnectionFactory() throws NamingException {
  }

  // public DBConnectionFactory(DataSource ds) throws NamingException {
  // this.ds = ds;
  // }

  public Connection createConnection() throws SQLException, NamingException {
    return getDataSource().getConnection();
  }

  public DataSource getDataSource() throws NamingException {
    if (ds == null)
      ds = (DataSource) lookup("jdbc/DerbyDB");
    return ds;
  }
}
