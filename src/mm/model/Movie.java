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
  private int rating;
  private List<Review> reviews;
  
  public Movie(int id, String title, String poster, String genre, String director, String actors, Timestamp dateAdded) {
    this.id = id;
    this.title = title;
    this.poster = poster;
    this.genre = genre;
    this.director = director;
    this.actors = actors;
    this.dateAdded = dateAdded;
  }

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

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

}
