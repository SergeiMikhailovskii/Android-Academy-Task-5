package asus.example.com.exercise5;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Film> films;
    private OnItemClickListener<Film> onItemClickListener;

    RecyclerViewAdapter(List<Film> films, Context context){
        this.films = films;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.preview_poster, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.itemView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            if (position!=RecyclerView.NO_POSITION){
                fireItemClicked(position, films.get(position));
            }
        });
        return viewHolder;
    }

    private void fireItemClicked(int position, Film film){
        if (onItemClickListener!=null){
            onItemClickListener.onItemClicked(position, film);
        }
    }

    void setOnItemClickListener(OnItemClickListener<Film> listener){
        onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Film film = films.get(i);
        Picasso.get().load(Uri.parse(film.getAvatar())).into(viewHolder.poster);
        viewHolder.name.setText(film.getTitle());
    }


    @Override
    public int getItemCount() {
        return films.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView poster;
        final TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster_preview);
            name = itemView.findViewById(R.id.name);
        }
    }

    public interface OnItemClickListener<T>{
        void onItemClicked(int position, T item);
    }
}
