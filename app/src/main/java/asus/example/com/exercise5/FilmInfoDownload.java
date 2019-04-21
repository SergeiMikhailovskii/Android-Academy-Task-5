package asus.example.com.exercise5;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmInfoDownload extends AsyncTask<Void, Void, Film> {

    private String title;

    FilmInfoDownload(String title){
        this.title = title;
    }

    @Override
    protected Film doInBackground(Void... voids) {
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
        final Call<Film> filmInfoCall = apiService.getFilmInfo("?"+Constants.KEY+"&t="+title);
        Film film = null;
        try {
            Response<Film> response = filmInfoCall.execute();
            film = response.body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return film;
    }
}
