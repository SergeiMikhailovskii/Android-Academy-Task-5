package asus.example.com.exercise5;

import android.os.AsyncTask;



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

    private String url;

    DataUtil(String url){
        this.url = url;
    }

    @Override
    protected List<Film> doInBackground(Void... voids) {
        List<Film> films = new ArrayList<>();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECT_AND_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.CONNECT_AND_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {
            Request original =  chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "auth-value");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        Retrofit retrofit = builder.build();
        APIService apiService  = retrofit.create(APIService.class);
        final Call<FilmsList> filmsCall = apiService.getFilms(url);
        try {
            Response<FilmsList> result = filmsCall.execute();
            for (int i = 0; i<Objects.requireNonNull(result.body()).films.size();i++){
                films.add(result.body().films.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return films;
    }

}