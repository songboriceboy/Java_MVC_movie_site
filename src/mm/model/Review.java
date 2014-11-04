package mm.model;

import java.sql.Timestamp;

public class Review {
  private int id;
  private String title;
  private String comment; // TODO: better type in DB
  private String username;
  private short rating;
  private Timestamp timeAdded;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public short getRating() {
    return rating;
  }
  public void setRating(short rating) {
    this.rating = rating;
  }
  public Timestamp getTimeAdded() {
    return timeAdded;
  }
  public void setTimeAdded(Timestamp timeAdded) {
    this.timeAdded = timeAdded;
  }
  
}
