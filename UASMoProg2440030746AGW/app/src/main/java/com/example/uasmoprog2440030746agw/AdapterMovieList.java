package com.example.uasmoprog2440030746agw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.MyViewHolder> {

    private Context context;
    private ArrayList<Movie> movieList;

    public AdapterMovieList(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(movieList.get(position).getMovieName());

//        Glide.with(context)
//                .load("https://image.tmdb.org/t/p/w500" + movieList.get(position).getMovieImg())
//                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.movieName);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
