package com.vararg.moviessample.screens.detailsscreen.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vararg.moviessample.R;
import com.vararg.moviessample.app.Consts;
import com.vararg.moviessample.screens.detailsscreen.presenter.DetailsScreenPresenter;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * Created by vararg on 12.04.2017.
 */

public class DetailsScreenView extends ConstraintLayout implements DetailsScreenCallbacks {

    @BindView(R.id.include) Toolbar toolbar;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;
    @BindView(R.id.backgroundPosterIv) ImageView backgroundPosterIv;
    @BindView(R.id.posterIv) ImageView posterIv;
    @BindView(R.id.scoreTv) TextView scoreTv;
    @BindView(R.id.ratingTv) TextView ratingTv;
    @BindView(R.id.releaseDateTv) TextView releaseDateTv;
    @BindView(R.id.movieNameTv) TextView movieNameTv;
    @BindView(R.id.overviewTv) TextView overviewTv;

    public DetailsScreenView(Context context) {
        this(context, null);
    }

    public DetailsScreenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailsScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            ButterKnife.bind(this);

            initToolbar();
        }
    }

    @Override
    public void onMovieReceived(MovieViewModel movie) {
        toolbarTitle.setText(movie.getTitle());
        scoreTv.setText(String.valueOf(movie.getScore()));
        ratingTv.setText(movie.isAdult() ? "R" : "PG-13");
        releaseDateTv.setText(new SimpleDateFormat("MMM dd, yy", Locale.ENGLISH).format(movie.getReleaseDate()));
        movieNameTv.setText(movie.getTitle());
        overviewTv.setText(movie.getOverview());

        Glide.with(getContext())
                .load(Consts.BASE_POSTER_URL + movie.getPosterUrl())
                .placeholder(R.color.imageLoadingPlaceholder)
                .error(R.color.imageLoadingError)
                .into(posterIv);

        Glide.with(getContext())
                .load(Consts.BASE_POSTER_URL + movie.getPosterUrl())
                .placeholder(R.color.imageLoadingPlaceholder)
                .error(R.color.imageLoadingPlaceholder)
                .bitmapTransform(new BlurTransformation(getContext()),
                        new ColorFilterTransformation(getContext(),
                                ContextCompat.getColor(getContext(), R.color.filterColor)))
                .into(backgroundPosterIv);
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

    @Inject
    void setPresenter(DetailsScreenPresenter presenter) {
        toolbar.setNavigationOnClickListener(view -> presenter.onBackPressed());
    }
}
