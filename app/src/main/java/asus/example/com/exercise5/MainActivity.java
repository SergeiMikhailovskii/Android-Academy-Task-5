package asus.example.com.exercise5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText searchEdit;
    private RecyclerView list;
    private List<Film> films;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEdit = findViewById(R.id.search_edit);
        list = findViewById(R.id.films_recyclerView);
        findViewById(R.id.search_button).setOnClickListener(searchListener);
    }

    private View.OnClickListener searchListener = v -> {
        list.setAdapter(null);
        String url = "?"+Constants.KEY+"&t="+searchEdit.getText().toString()+"&page=1&s="+searchEdit.getText().toString();
        DataUtil dataUtil = new DataUtil(url);
        list.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.rows)));
        try {
            films = dataUtil.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(films, this);
        recyclerViewAdapter.setOnItemClickListener(this::onListClick);

        list.setAdapter(recyclerViewAdapter);

    };

    private void onListClick(int position, Film item){
        Intent intent = new Intent(this, FilmDetailActivity.class);
        intent.putExtra(Constants.FILM_PUT_EXTRA, films.get(position).getTitle());
        startActivity(intent);

    }

}
