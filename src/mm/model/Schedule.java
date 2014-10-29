package mm.model;

import java.util.Date;

public class Schedule {
  private int id;
  private Cinema cinema;
  private Movie movie;
  private Date date;
  private String timeslot;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Cinema getCinema() {
    return cinema;
  }
  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }
  public Movie getMovie() {
    return movie;
  }
  public void setMovie(Movie movie) {
    this.movie = movie;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public String getTimeslot() {
    return timeslot;
  }
  public void setTimeslot(String timeslot) {
    this.timeslot = timeslot;
  }
  
}
