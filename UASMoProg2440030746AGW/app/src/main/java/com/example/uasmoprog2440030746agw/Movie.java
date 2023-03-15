package com.example.uasmoprog2440030746agw;

public class Movie {
    String movieName;
    String movieImg;

    public Movie() {
    }

    public Movie(String movieName, String movieImg) {
        this.movieName = movieName;
        this.movieImg = movieImg;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg;
    }
}
