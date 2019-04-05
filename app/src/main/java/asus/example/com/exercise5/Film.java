package asus.example.com.exercise5;

import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private String year;
    @SerializedName("Released")
    private String released;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Director")
    private String director;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Language")
    private String language;
    @SerializedName("Country")
    private String country;
    @SerializedName("imdbRating")
    private String imdbRating;
    @SerializedName("Poster")
    private String avatar;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getAvatar() {
        return avatar;
    }

    public Film(String title, String year, String released, String runtime, String director,
                String actors, String plot, String language, String country, String imdbRating, String avatar) {
        this.title = title;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.imdbRating = imdbRating;
        this.avatar = avatar;
    }

}
