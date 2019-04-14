package asus.example.com.exercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText searchEdit;
    private RecyclerView list;


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
        String url = "?apikey=956febbc&t="+searchEdit.getText().toString()+"&page=1&s="+searchEdit.getText().toString();
        DataUtil dataUtil = new DataUtil(url);
        List<Film> films = null;
        list.setLayoutManager(new GridLayoutManager(this, 2));
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

    };

}
