package mm.model;

/**
 * Helper POJO, for convenience of booking.jsp
 * @author ryan
 */
public class BookingPerSchedule {
  private int scheduleID;
  private boolean soldOut;
  private String strRep;
  
  public int getScheduleID() {
    return scheduleID;
  }
  public void setScheduleID(int scheduleID) {
    this.scheduleID = scheduleID;
  }
  public boolean getSoldOut() {
    return soldOut;
  }
  public void setSoldOut(boolean soldOut) {
    this.soldOut = soldOut;
  }
  public String getStrRep() {
    return strRep;
  }
  public void setStrRep(String strRep) {
    this.strRep = strRep;
  }
  
}
