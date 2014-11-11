package mm.model.helper;

/**
 * Helper POJO, for convenience of booking.jsp
 * @author ryan
 */
public class BookingPerSchedule {
  private int scheduleID;
  private boolean isSoldOut;
  private String strRep;
  
  public int getScheduleID() {
    return scheduleID;
  }
  public void setScheduleID(int scheduleID) {
    this.scheduleID = scheduleID;
  }
  public boolean getIsSoldOut() {
    return isSoldOut;
  }
  public void setIsSoldOut(boolean soldOut) {
    this.isSoldOut = soldOut;
  }
  public String getStrRep() {
    return strRep;
  }
  public void setStrRep(String strRep) {
    this.strRep = strRep;
  }
  
}
