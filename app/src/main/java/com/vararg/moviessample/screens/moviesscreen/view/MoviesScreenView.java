package com.vararg.moviessample.screens.moviesscreen.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.vararg.moviessample.R;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;
import com.vararg.moviessample.screens.moviesscreen.presenter.MoviesScreenPresenter;
import com.vararg.moviessample.widgets.EndlessRecyclerViewOnScrollListener;
import com.vararg.moviessample.widgets.SpacesItemDecoration;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vararg on 10.04.2017.
 */

public class MoviesScreenView extends ConstraintLayout implements MovieScreenCallbacks {

    @BindView(R.id.include) Toolbar toolbar;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private MoviesAdapter moviesAdapter;
    private OnLoadMoreListener onLoadMoreListener;

    public MoviesScreenView(Context context) {
        this(context, null);
    }

    public MoviesScreenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoviesScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            ButterKnife.bind(this);

            initToolbar();
            initList();
        }
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoviesReceived(Collection<MovieViewModel> movies) {
        moviesAdapter.setMovies(movies);
    }

    @Override
    public void onNewMoviesReceived(Collection<MovieViewModel> movies) {
        moviesAdapter.addMovies(movies);
    }

    private void initToolbar(){
        toolbarTitle.setText(R.string.movies_screen_name);
    }

    private void initList() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.addOnScrollListener(
                new EndlessRecyclerViewOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        onLoadMoreListener.onLoadMore();
                    }
                });
        recyclerView.setLayoutManager(layoutManager);

        moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(
                        getContext().getResources().getDimensionPixelOffset(R.dimen.item_list_space)));
    }

    @Inject
    void setPresenter(MoviesScreenPresenter presenter) {
        refreshLayout.setOnRefreshListener(presenter::fetchStartData);

        moviesAdapter.setOnItemSelectedListener(presenter::onMovieSelected);

        onLoadMoreListener = presenter::fetchNewData;
    }

    private interface OnLoadMoreListener {
        void onLoadMore();
    }

}
