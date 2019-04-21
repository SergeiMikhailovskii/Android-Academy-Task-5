package asus.example.com.exercise5;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {

    @GET
    Call<FilmsList> getFilms(@Url String url);

    @GET
    Call<Film> getFilmInfo(@Url String url);

}

//http://www.omdbapi.com/?apikey=956febbc&t="Batman"