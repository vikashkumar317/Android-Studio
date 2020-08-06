package com.example.learntablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Movie> mMovie;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, List<Movie> movies) {
        mInflater = LayoutInflater.from(context);
        mMovie = movies;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_item, parent, false);
        return new CustomViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Movie current = mMovie.get(position);
        Glide.with(holder.movieImageView.getContext()).load(current.getImage()).into(holder.movieImageView);
        holder.title.setText(current.getTitle());
        holder.price.setText(current.getPrice());
    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final ImageView movieImageView;
        public final TextView title, price;
        final CustomAdapter mAdapter;

        public CustomViewHolder(@NonNull View itemView, CustomAdapter customAdapter) {
            super(itemView);
            movieImageView = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            this.mAdapter = customAdapter;
        }
    }
}
