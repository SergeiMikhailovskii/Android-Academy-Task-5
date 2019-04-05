package asus.example.com.exercise5;


import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("?apikey=956febbc&t=batman")
    Call<Film> getFilms();

}
