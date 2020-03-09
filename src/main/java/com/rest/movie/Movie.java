package com.rest.movie;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private int id;
    private String title;
    private String genre;
    private double rate;
    private String description;
    private int rateNum;

    public Movie() {
        this.id = 0;
        this.title = "title";
        this.genre = "genre";
        this.rate = 10;
        this.description = "desc";
        this.rateNum = 10;
    }
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
        this.genre = "genre";
        this.rate = 10;
        this.description = "desc";
        this.rateNum = 10;
    }
    public Movie(int id, String title, String genre, double rate, String description, int rateNum) {
        this.id = id;
        this.title = title;
        this.genre = "";
        this.rate = 0.0;
        this.description = "";
        this.rateNum = 0;
    }


    @Basic
    @Column(name = "id", columnDefinition="int")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", columnDefinition="text")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "genre", columnDefinition="text")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "rate", columnDefinition="numeric")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "description", columnDefinition="text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "rateNum", columnDefinition="int")
    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", rateNum=" + rateNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Double.compare(movie.rate, rate) != 0) return false;
        if (rateNum != movie.rateNum) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + rateNum;
        return result;
    }
}
