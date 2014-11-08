package mm.model;

public class Booking {
  private int id;
  private int viewerID;
  private int scheduleID;
  private int numTickets;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getViewerID() {
    return viewerID;
  }
  public void setViewerID(int viewerID) {
    this.viewerID = viewerID;
  }
  public int getScheduleID() {
    return scheduleID;
  }
  public void setScheduleID(int scheduleID) {
    this.scheduleID = scheduleID;
  }
  public int getNumTickets() {
    return numTickets;
  }
  public void setNumTickets(int numTickets) {
    this.numTickets = numTickets;
  }
  
}
