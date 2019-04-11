package asus.example.com.exercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView list = findViewById(R.id.films_recyclerView);
        list.setLayoutManager(new GridLayoutManager(this, 2));
        DataUtil dataUtil = new DataUtil();
        List<Film> films = null;
        try {
            films = dataUtil.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(films, this);
        recyclerViewAdapter.setOnItemClickListener((position, item) ->
                Toast.makeText(getApplicationContext(), "On item clicked", Toast.LENGTH_SHORT)
                        .show());
        list.setAdapter(recyclerViewAdapter);

    }
}
