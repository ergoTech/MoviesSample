package com.vararg.moviessample.screens.moviesscreen.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vararg.moviessample.R;
import com.vararg.moviessample.app.Consts;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;
import com.vararg.moviessample.widgets.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vararg on 18.02.2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ProductViewHolder> {

    private List<MovieViewModel> movies;
    private OnMovieSelectedListener movieSelectedListener;
    private OnPositionSelectedListener positionSelectedListener;

    public MoviesAdapter() {
        movies = new ArrayList<>();

        positionSelectedListener = pos -> {
            if (movieSelectedListener != null)
                movieSelectedListener.onItemSelected(getMovieByPosition(pos));
        };
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_movie, parent, false),
                positionSelectedListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private MovieViewModel getMovieByPosition(int position) {
        return movies.get(position);
    }

    public void setMovies(Collection<MovieViewModel> newProducts) {
        this.movies.clear();
        this.movies.addAll(newProducts);
        notifyDataSetChanged();
    }

    public void addMovies(Collection<MovieViewModel> newProducts) {
        int start = getItemCount();
        this.movies.addAll(newProducts);
        notifyItemRangeInserted(start, newProducts.size());
    }

    public void setOnItemSelectedListener(OnMovieSelectedListener listener) {
        movieSelectedListener = listener;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView) ImageView imageView;

        private ProductViewHolder(View view, OnPositionSelectedListener listener) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(v -> {
                if (listener != null) listener.onItemSelected(getAdapterPosition());
            });
        }

        private void bind(MovieViewModel movie){
            Glide.with(itemView.getContext())
                    .load(Consts.BASE_POSTER_URL + movie.getPosterUrl())
                    .placeholder(R.color.imageLoadingPlaceholder)
                    .error(R.color.imageLoadingError)
                    .into(imageView);
        }

    }

    public interface OnMovieSelectedListener extends OnItemSelectedListener<MovieViewModel> {}

    private interface OnPositionSelectedListener extends OnItemSelectedListener<Integer> {}
}
