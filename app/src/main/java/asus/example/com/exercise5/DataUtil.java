package asus.example.com.exercise5;

import android.os.AsyncTask;
import android.util.Log;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataUtil extends AsyncTask<Void, Void, List<Film>> {

    public APIService api = new NetworkModule().apiService;
    private static List <Film> films = new ArrayList<>();

    @Override
    protected List<Film> doInBackground(Void... voids) {

        String BaseUrl = "http://www.omdbapi.com/";
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {
            Request original =  chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "auth-value");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        Retrofit retrofit = builder.build();
        APIService apiService  = retrofit.create(APIService.class);
        final Call<FilmsList> filmsCall = apiService.getFilms();
        try {
            Response<FilmsList> result = filmsCall.execute();
            for (int i = 0; i<Objects.requireNonNull(result.body()).films.size();i++){
                films.add(result.body().films.get(i));
            }
            Log.i("DataUtil", "Body added");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("DataUtil", "size = "+films.size());
        return films;
    }

}