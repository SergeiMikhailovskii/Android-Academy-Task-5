package asus.example.com.exercise5;



import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("?apikey=956febbc&t=Batman&page=1&s=Batma")
    Call<FilmsList> getFilms();

}

//http://www.omdbapi.com/?apikey=956febbc&t=Batman&page=2&s=Batman