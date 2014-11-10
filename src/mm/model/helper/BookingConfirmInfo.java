package mm.model.helper;

import mm.model.Movie;

public class BookingConfirmInfo {
  private Movie movie;
  private int bookingID;
  private String cinema;
  private String date;
  private String timeslot;
  private int unitPrice;
  private int numTickets;
  private int totalPrice;
  
  public Movie getMovie() {
    return movie;
  }
  public void setMovie(Movie movie) {
    this.movie = movie;
  }
  public int getBookingID() {
    return bookingID;
  }
  public void setBookingID(int bookingID) {
    this.bookingID = bookingID;
  }
  public String getCinema() {
    return cinema;
  }
  public void setCinema(String cinema) {
    this.cinema = cinema;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getTimeslot() {
    return timeslot;
  }
  public void setTimeslot(String timeslot) {
    this.timeslot = timeslot;
  }
  public int getUnitPrice() {
    return unitPrice;
  }
  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }
  public int getNumTickets() {
    return numTickets;
  }
  public void setNumTickets(int numTickets) {
    this.numTickets = numTickets;
  }
  public int getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
  @Override
  public String toString() {
    return "BookingConfirmInfo [movie=" + movie.getTitle() + ", bookingID=" + bookingID + ", cinema=" + cinema + ", date=" + date + ", timeslot="
        + timeslot + ", unitPrice=" + unitPrice + ", numTickets=" + numTickets + ", totalPrice=" + totalPrice + "]";
  }
  
}
