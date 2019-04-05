package asus.example.com.exercise5;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataUtil extends AsyncTask<Void, Void, Void> {

    public APIService api = new NetworkModule().apiService;
    private static final String Tag = "DataUtil";
    private static List <Film> films = new ArrayList<>();

    static List<Film> generateFilms(OnLoaded callback){
        Log.i(Tag, "In generate films");

//        filmsCall.enqueue(new Callback<Film>() {
//            @Override
//            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
//                if (response.isSuccessful()){
//                    Film film = response.body();
//                    films.add(film);
//                    Log.i(Tag, "Response: "+Objects.requireNonNull(response.body()).getTitle());
//                    callback.onLoaded(film);
//                }
//                else {
//                    Log.i(Tag, "Response code: "+response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
//                Log.i(getClass().getSimpleName(), "Error: "+t);
//            }
//        });

        Log.i(Tag, "Size = "+films.size());
        return films;
    }

    @Override
    protected Void doInBackground(Void... voids) {

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
        final Call<Film> filmsCall = apiService.getFilms();
        try {
            Response<Film> result = filmsCall.execute();
            films.add(result.body());
            Log.i("DataUtil", "Body added");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface OnLoaded{
        void onLoaded(Film film);
    }

}
