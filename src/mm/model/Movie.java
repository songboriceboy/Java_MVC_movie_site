package mm.model;

import java.sql.Timestamp;
import java.util.List;

public class Movie {
  private int id;
  private String title;
  private String poster;
  private String genre;
  private String synopsis;
  private String director;
  private String actors;
  private Timestamp dateAdded;
  private float rating;
  private int numReviews;
  private List<Review> reviews;
  private String status;

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

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }

  public Timestamp getDateAdded() {
    return dateAdded;
  }

  public void setDateAdded(Timestamp dateAdded) {
    this.dateAdded = dateAdded;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public int getNumReviews() {
    return numReviews;
  }

  public void setNumReviews(int numReviews) {
    this.numReviews = numReviews;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Movie other = (Movie) obj;
    if (id != other.id)
      return false;
    return true;
  }

  public static Movie dummyMovie() {
    Movie m = new Movie();
    m.setId(DUMMY_ID);
    m.setTitle(DUMMY_TITLE);
    return m ;
  }

  public boolean isDummyMovie() {
    return (id==DUMMY_ID && DUMMY_TITLE.equals(title));
  }

  private static final int DUMMY_ID = -1;
  private static final String DUMMY_TITLE = "This movie should not exist";
}
