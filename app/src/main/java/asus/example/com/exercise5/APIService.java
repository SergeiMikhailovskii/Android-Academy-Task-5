package asus.example.com.exercise5;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {
    //@GET("?apikey=956febbc&t=Batman&page=1&s=Batman")
    @GET
    Call<FilmsList> getFilms(@Url String url);

}

//http://www.omdbapi.com/?apikey=956febbc&t=Batman&page=2&s=Batman