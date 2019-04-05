package asus.example.com.exercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private final List<Film> films = DataUtil.generateFilms(new DataUtil.OnLoaded() {
        @Override
        public void onLoaded(Film film) {
            films.add(film);
            Log.i("DataUtil", "Film added");
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.films_recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(films, this);
        recyclerViewAdapter.setOnItemClickListener((position, item) ->
                Toast.makeText(getApplicationContext(), "On item clicked", Toast.LENGTH_SHORT)
                        .show());
        list.setAdapter(recyclerViewAdapter);

    }
}
