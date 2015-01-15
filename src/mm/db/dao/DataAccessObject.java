package mm.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.NamingException;

import mm.db.jdbc.QuickDBConnectionFactory;
import mm.model.Cinema;
import mm.model.Movie;
import mm.model.Owner;
import mm.model.Review;
import mm.model.Schedule;
import mm.model.Viewer;
import mm.model.helper.BookingInfo;
import mm.util.Constants;
import mm.util.Util;

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
        // TODO: is it necessary to convert NULL string to empty string? nickname, firstname, lastname
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

  public void addMovie(String title, String poster, String genre, String synopsis, String director, String actors) {
    try {
      PreparedStatement stmt = con.prepareStatement(
          "INSERT INTO movies VALUES (default,?,?,?,?,?,CURRENT_TIMESTAMP,?)");
      stmt.setString(1, title);
      stmt.setString(2, poster);
      stmt.setString(3, genre);
      stmt.setString(4, director);
      stmt.setString(5, actors);
      stmt.setString(6, synopsis);
      if (stmt.executeUpdate() == 1)
        logger.info("Added movie: " + title);
      else
        warnZeroRowAffected();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int findMovieIdByTitle(String title) {
    try {
      PreparedStatement stmt = con.prepareStatement("SELECT id FROM movies WHERE title = ?");
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return rs.getInt("id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  private void warnZeroRowAffected() {
    logger.warning("0 row affected.");
  }

  public List<Movie> listNowShowingMovies() {
    return listHomepageMovies(false);
  }

  public List<Movie> listCoomingSoonMovies() {
    return listHomepageMovies(true);
  }

  private List<Movie> listHomepageMovies(boolean comingSoon) {
    List<Movie> ret = new ArrayList<Movie>();
    String operator = comingSoon ? ">" : "=";
    String sql = "SELECT m.*, s.mindate, r.avgrating, r.numreviews FROM movies m "
        + "LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r "
        + "ON r.movieid = m.id, "
        + "(SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s "
        + "WHERE s.movieid = m.id AND s.mindate "+operator+" CURRENT_DATE "
        + "ORDER BY m.dateAdded DESC";
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Movie m = comingSoon ? makeMovieWithStatusComingSoon(rs) : makeMovieWithRating(rs);
        ret.add(m);
      }
      return ret ;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Movie makeMovie(ResultSet rs) throws SQLException {
    Movie m = new Movie();
    m.setId(rs.getInt("id"));
    m.setTitle(rs.getString("title"));
    m.setPoster(rs.getString("poster"));
    m.setGenre(rs.getString("genre"));
    m.setSynopsis(rs.getString("synopsis"));
    m.setDirector(rs.getString("director"));
    m.setActors(rs.getString("actors"));
    m.setDateAdded(rs.getTimestamp("dateAdded"));
    return m;
  }

  private Movie makeMovieWithRating(ResultSet rs) throws SQLException {
    Movie m = makeMovie(rs);
    m.setRating(rs.getFloat("avgRating"));
    m.setNumReviews(rs.getInt("numReviews"));
    return m;
  }

  private Movie makeMovieWithStatus(ResultSet rs) throws SQLException {
    Movie m = makeMovieWithRating(rs);
    Date d = rs.getDate("mindate");
    if (d == null)
      m.setStatus("No future showing time scheduled.");
    else {
      // d must be >= today as it is specified in the SQL
      java.util.Date today = new java.util.Date();
      if (Util.isDayAfter(d, today))
        m.setStatus("Coming Soon on " + d); // TODO: date format Oct 12 2014
      else // must be equal
        m.setStatus("Now Showing");
    }
    return m;
  }

  private Movie makeMovieWithStatusComingSoon(ResultSet rs) throws SQLException {
    Movie m = makeMovieWithRating(rs);
    m.setStatus("Coming Soon on " + rs.getDate("mindate")); // TODO: date format Oct 12 2014
    return m;
  }

  public List<Movie> searchMovies(String keyword, boolean byTitle, boolean byGenre) {
    if (keyword == null || keyword.trim().equals("")) {
      byTitle = false;
      byGenre = false;
    } else
      keyword = keyword.toLowerCase();
    
    StringBuilder sql = new StringBuilder(
          "SELECT * FROM movies m"
        + " LEFT JOIN (SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s"
        + " ON s.movieid = m.id"
        + " LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r"
        + " ON r.movieid = m.id");
    if (byTitle && byGenre)
      sql.append(" WHERE (LOWER(m.title) LIKE '%"+keyword+"%' or LOWER(m.genre) LIKE '%"+keyword+"%')");
    else if (byTitle && !byGenre)
      sql.append(" WHERE (LOWER(m.title) LIKE '%"+keyword+"%')");
    else if (!byTitle && byGenre)
      sql.append(" WHERE (LOWER(m.genre) LIKE '%"+keyword+"%')");
    sql.append(" ORDER BY m.dateAdded DESC");
    
    List<Movie> ret = new ArrayList<Movie>();
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql.toString());
      while (rs.next()) {
        ret.add(makeMovieWithStatus(rs));
      }
      return ret ;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Movie findMovie(int id) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM movies m WHERE m.id="+id);
      if (rs.next()) {
        Movie m = makeMovie(rs);
        return m;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Movie findMovieDetails(int id) {
    // TODO: do not query reviews (avgRating, numReviews) of coming soon movies
    String sql =
        "SELECT * FROM movies m"
      + " LEFT JOIN (SELECT movieid, MIN(date0) AS mindate FROM schedules WHERE date0 >= CURRENT_DATE GROUP BY movieid) s"
      + " ON s.movieid = m.id"
      + " LEFT JOIN (SELECT movieid, AVG(rating) AS avgrating, COUNT(*) AS numreviews FROM reviews GROUP BY movieid) r"
      + " ON r.movieid = m.id"
      + " WHERE m.id="+id;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        Movie m = makeMovieWithStatus(rs);
        return m;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Review> findReviewsByMovie(int id) {
    List<Review> ret = new ArrayList<Review>();
    String sql = "SELECT v.username, r.*"
        + " FROM reviews r, viewers v WHERE r.viewerid = v.id AND r.movieid = "+id
        + " ORDER BY r.timeAdded desc";
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Review r = new Review();
        r.setId(rs.getInt("id"));
        r.setTitle(rs.getString("title"));
        r.setComment(rs.getString("comment"));
        r.setUsername(rs.getString("username"));
        r.setRating(rs.getFloat("rating"));
        r.setTimeAdded(rs.getTimestamp("timeAdded"));
        ret.add(r);
      }
      return ret;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Map<Integer, String> findCinemasIdLocationMap() {
    Map<Integer, String> ret = new HashMap<Integer, String>();
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT id, location FROM cinemas");
      while (rs.next()) {
        ret.put(rs.getInt("id"), rs.getString("location"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ret ;
  }

  public Map<Integer, String> findCinemasIdLocationMapWithMovieSchedule(int movieid) {
    Map<Integer, String> ret = new HashMap<Integer, String>();
    String sql = "SELECT * FROM cinemas WHERE id IN (SELECT DISTINCT cinemaid FROM schedules WHERE movieid = ?)";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, movieid);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        ret.put(rs.getInt("id"), rs.getString("location"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ret ;
  }

  public Date findScheduleLastDayByCinema(int cinemaid) {
    String sql = "SELECT MAX(date0) AS maxDate FROM schedules WHERE cinemaid = "+cinemaid+" GROUP BY cinemaid";
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        return rs.getDate("maxDate");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Schedule> findSchedulesByCinemaTimeslotFromToday(int cinemaid, String ts) {
    List<Schedule> ls = new ArrayList<Schedule>();
    String sql = "SELECT s.id as sid, s.date0, s.timeslot, m.* FROM schedules s "
        + "LEFT JOIN movies m ON m.id = s.movieid "
        + "WHERE s.cinemaid = ? AND s.timeslot = ? AND s.date0 >= CURRENT_DATE ORDER BY s.date0";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, cinemaid);
      stmt.setString(2, ts);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        ls.add(makeScheduleWithMovie(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ls;
  }

  private Schedule makeScheduleWithMovie(ResultSet rs) throws SQLException {
    Schedule s = new Schedule();
    s.setId(rs.getInt("sid"));
    s.setDate(rs.getDate("date0"));
    s.setTimeslot(rs.getString("timeslot"));
    s.setMovie(makeMovie(rs));
    return s ;
  }

  public boolean addMovieTime(int movieid, int cinemaid, String timeslot, java.util.Date startDate, java.util.Date endDate) {
    boolean success = true;
    int n = 0;
    try {
      con.setAutoCommit(false);
      PreparedStatement stmt = con.prepareStatement("INSERT INTO schedules VALUES (DEFAULT,?,?,?,?)");
      stmt.setInt(1, cinemaid);
      stmt.setInt(2, movieid);
      stmt.setString(4, timeslot);
      for (java.util.Date d : Util.getDaysInBetween_Inclusive(startDate, endDate)) {
        stmt.setDate(3, new Date(d.getTime()));
        n += stmt.executeUpdate();
      }
      con.commit();
      con.setAutoCommit(true);
      logger.info("schedule inserted: "+n+" rows.");
    } catch (SQLIntegrityConstraintViolationException e) {
      try {
        logger.info("schedule time occupied, transaction rolling back...");
        con.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      success = false;
    } catch (SQLException e) {
      e.printStackTrace();
      success = false;
    } 
    return success;
  }

  public Cinema findCinema(int id) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM cinemas WHERE id="+id);
      if (rs.next()) {
        Cinema c = makeCinema(rs);
        logger.info("found cinema: "+c.getLocation());
        return c;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Cinema makeCinema(ResultSet rs) throws SQLException {
    Cinema c = new Cinema();
    c.setId(rs.getInt("id"));
    c.setLocation(rs.getString("location"));
    c.setSeatCapacity(rs.getInt("seatCapacity"));
    c.setUnitPrice(rs.getInt("unitPrice"));
    return c ;
  }

  public List<Schedule> findScheduleByCinemaMovie(int cinemaid, int movieid) {
    List<Schedule> ls = new ArrayList<Schedule>();
    String sql = "SELECT * FROM schedules s "
        + "LEFT JOIN (SELECT scheduleid, SUM(numTickets) AS sumTickets FROM bookings GROUP BY scheduleid) b ON s.id = b.scheduleid "
        + "WHERE s.date0 >= CURRENT_DATE AND s.cinemaid = ? AND s.movieid = ? "
        + "ORDER BY s.date0, s.timeslot";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, cinemaid);
      stmt.setInt(2, movieid);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Schedule s = new Schedule();
        s.setId(rs.getInt("id"));
        s.setDate(rs.getDate("date0"));
        s.setTimeslot(rs.getString("timeslot"));
        s.setNumTickets(rs.getInt("sumTickets")); // rs.getInt("sumTickets") will be 0 instead of NULL if no data
        ls.add(s);
//        logger.info("found schedule: id="+s.getId()+" sumTickets="+s.getNumTickets());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    logger.info("found "+ls.size()+" schedules");
    return ls;
  }

  public int findNumSeatsLeftBySchedule(int scheduleID) {
    String sql = "SELECT c.seatCapacity,  b.sumTickets FROM cinemas c, schedules s "
        + " LEFT JOIN (SELECT scheduleID, SUM(numTickets) as sumTickets FROM bookings WHERE scheduleID = ? GROUP BY scheduleID) b ON s.id = b.scheduleID "
        + "WHERE c.id = s.cinemaID AND s.id = ?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, scheduleID);
      stmt.setInt(2, scheduleID);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int sc = rs.getInt("seatCapacity");
        int st = rs.getInt("sumTickets");
        int n = sc - st;
        logger.info("numSeatsLeft="+n);
        return n;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public void addBooking(int viewerID, int scheduleID, int nTickets) {
    String sql = "INSERT INTO bookings VALUES (DEFAULT,?,?,?)";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, viewerID);
      stmt.setInt(2, scheduleID);
      stmt.setInt(3, nTickets);
      if (stmt.executeUpdate() > 0)
        logger.info("inserted");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int findBookingIdByViewerSchedule(int viewerID, int scheduleID) {
    // THIS QUERY IS NOT GOOD: MAX(id) is the newest id IFF id is incremented.
    String sql = "SELECT MAX(id) AS maxID FROM bookings WHERE viewerID = ? AND scheduleID = ?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, viewerID);
      stmt.setInt(2, scheduleID);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) { 
        int id = rs.getInt("maxID");
        logger.info("found booking ID: "+id);
        return id;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  // TODO: ID column of each table uses unique name ('movieID', 'scheduelID' ...) instead of 'id'
  public BookingInfo findBookingInfoById(int bookingID) {
    BookingInfo bi = null;
    String sql = "SELECT m.*, b.id AS bookingID, b.numTickets, s.id AS sid, s.date0, s.timeslot, c.location, c.unitPrice "
        + " FROM bookings b, schedules s, movies m, cinemas c "
        + " WHERE b.scheduleID = s.id AND s.movieID = m.id AND s.cinemaID = c.id AND b.id = "+bookingID;
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        bi = makeBookingInfo(rs);
        logger.info(bi.toString());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bi;
  }

  private BookingInfo makeBookingInfo(ResultSet rs) throws SQLException {
    BookingInfo bi = new BookingInfo();
    Schedule s = makeScheduleWithMovie(rs);
    SimpleDateFormat fmt = new SimpleDateFormat("d MMM yyyy");
    bi.setDate(fmt.format(s.getDate()));
    bi.setTimeslot(Constants.tsMap.get(s.getTimeslot()));
    bi.setMovie(s.getMovie());
    bi.setBookingID(rs.getInt("bookingID"));
    bi.setNumTickets(rs.getInt("numTickets"));
    bi.setCinema(rs.getString("location"));
    bi.setUnitPrice(rs.getInt("unitPrice"));
    bi.setTotalPrice(bi.getUnitPrice() * bi.getNumTickets());
    return bi;
  }

  public List<BookingInfo> findBookingInfosByViewer(int viewerID) {
    List<BookingInfo> lb = new ArrayList<BookingInfo>();
    String sql = "SELECT m.*, b.id AS bookingID, b.numTickets, s.id AS sid, s.date0, s.timeslot, c.location, c.unitPrice "
        + " FROM bookings b, schedules s, movies m, cinemas c "
        + " WHERE b.scheduleID = s.id AND s.movieID = m.id AND s.cinemaID = c.id AND b.viewerID = "+viewerID
        + " ORDER BY b.id DESC";    
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        BookingInfo bi = makeBookingInfo(rs);
        lb.add(bi);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    logger.info("list size="+lb.size());
    return lb ;
  }

  public void addReview(int viewerID, int movieID, String title, float rating, String comment) {
    String sql = "INSERT INTO reviews VALUES (DEFAULT,?,?,?,?,?,CURRENT_TIMESTAMP)";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, viewerID);
      stmt.setInt(2, movieID);
      stmt.setString(3, title);
      stmt.setString(4, comment);
      stmt.setFloat(5,rating);
      if (stmt.executeUpdate() > 0)
        logger.info("added review: "+title);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
