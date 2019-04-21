package asus.example.com.exercise5;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class FilmDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        String title = getIntent().getStringExtra("FILM_NAME");

        ImageView avatarImageView = findViewById(R.id.avatar_imageview);
        TextView actorsTextView = findViewById(R.id.actors_textview);
        TextView titleTextView = findViewById(R.id.title_textview);
        TextView yearTextView = findViewById(R.id.year_textview);
        TextView runtimeTextView = findViewById(R.id.runtime_textview);
        TextView directorTextView = findViewById(R.id.director_textview);
        TextView plotTextView = findViewById(R.id.plot_textview);
        TextView languageTextView = findViewById(R.id.language_textview);
        TextView countryTextView = findViewById(R.id.country_textview);
        TextView imdbRatingTextView = findViewById(R.id.imdb_rating_textview);

        FilmInfoDownload filmInfoDownload = new FilmInfoDownload(title);
        Film film = null;

        try {
            film = filmInfoDownload.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Picasso.get().load(Uri.parse(Objects.requireNonNull(film).getAvatar())).into(avatarImageView);
        actorsTextView.append(film.getActors());
        titleTextView.append(film.getTitle());
        yearTextView.append(film.getYear());
        runtimeTextView.append(film.getRuntime());
        directorTextView.append(film.getDirector());
        plotTextView.append(film.getPlot());
        languageTextView.append(film.getLanguage());
        countryTextView.append(film.getCountry());
        imdbRatingTextView.append(film.getImdbRating());

    }
}
