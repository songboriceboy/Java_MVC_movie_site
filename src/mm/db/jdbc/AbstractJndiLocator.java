package mm.db.jdbc;

import javax.naming.*;

public abstract class AbstractJndiLocator {
  protected InitialContext ctx;
  protected Context envContext;

  public AbstractJndiLocator() throws NamingException {
    ctx = new InitialContext();
    envContext = (Context) ctx.lookup("java:/comp/env");
  }

  /**
   * If this returns null, caller should deal with it
   */
  public Object lookup(String name) throws NamingException {
    return envContext.lookup(name);
  }
}
