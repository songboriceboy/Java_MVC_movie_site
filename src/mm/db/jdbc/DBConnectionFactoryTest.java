package mm.db.jdbc;

import java.sql.Connection;

import javax.naming.*;

import junit.framework.TestCase;

public class DBConnectionFactoryTest extends TestCase {

  public void setUp() throws Exception {
    super.setUp();
    // To test JNDI outside Tomcat, we need to set these
    // values manually ... (just for testing purposes)
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

    // Create InitialContext with java:/comp/env/jdbc
    InitialContext ic = new InitialContext();
    ic.createSubcontext("java:");
    ic.createSubcontext("java:/comp");
    ic.createSubcontext("java:/comp/env");
    ic.createSubcontext("java:/comp/env/jdbc");

    // Construct BasicDataSource reference
    Reference ref = new Reference("javax.sql.DataSource", "org.apache.commons.dbcp.BasicDataSourceFactory", null);
    ref.add(new StringRefAddr("driverClassName", "org.apache.derby.jdbc.ClientDriver"));
    ref.add(new StringRefAddr("url", "jdbc:derby://localhost:1527/miaowumovie"));
    ref.add(new StringRefAddr("username", "sa"));
    ref.add(new StringRefAddr("password", "sa"));
    ic.bind("java:/comp/env/jdbc/DerbyDB", ref);
  }

  public DBConnectionFactoryTest(String arg0) {
    super(arg0);
  }

  public static void main(String[] args) {
    // junit.swingui.TestRunner.run(DBUtilsTest.class);
    junit.textui.TestRunner.run(DBConnectionFactoryTest.class);
  }

  public void testCreateConnection() throws Exception {
    DBConnectionFactory services = new DBConnectionFactory();
    Connection con = services.createConnection();
    assertNotNull(con);
    if (con != null)
      con.close();
  }
}
