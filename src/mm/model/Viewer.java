package mm.model;

public class Viewer {
  private int id;
  private String username;
  private String password;
  private String nickname;
  private String firstname;
  private String lastname;
  private String email;
  private boolean activated;
  
  public Viewer(int id, String username, String password, String nickname, String firstname, String lastname,
      String email,boolean activated) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.activated = activated;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public boolean isActivated() {
    return activated;
  }
  public void setActivated(boolean activated) {
    this.activated = activated;
  }
}
