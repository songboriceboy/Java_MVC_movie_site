package mm.model;

public class Cinema {
  private int id;
  private String location;
  private int seatCapacity;
  private int unitPrice;
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public int getSeatCapacity() {
    return seatCapacity;
  }
  public void setSeatCapacity(int seatCapacity) {
    this.seatCapacity = seatCapacity;
  }
  public int getUnitPrice() {
    return unitPrice;
  }
  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

}
