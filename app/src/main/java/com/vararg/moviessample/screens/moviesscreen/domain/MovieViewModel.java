package com.vararg.moviessample.screens.moviesscreen.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by vararg on 10.04.2017.
 */

public class MovieViewModel implements Parcelable {

    private long id;

    private boolean isAdult;

    private String overview;

    private Date releaseDate;

    private String title;

    private String originalTitle;

    private float score;

    private String posterUrl;

    public MovieViewModel(long id, boolean isAdult, String overview, Date releaseDate, String title,
                          String originalTitle, float score, String posterUrl) {
        this.id = id;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.title = title;
        this.originalTitle = originalTitle;
        this.score = score;
        this.posterUrl = posterUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeByte(this.isAdult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeFloat(this.score);
        dest.writeString(this.posterUrl);
    }

    protected MovieViewModel(Parcel in) {
        this.id = in.readLong();
        this.isAdult = in.readByte() != 0;
        this.overview = in.readString();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.score = in.readFloat();
        this.posterUrl = in.readString();
    }

    public static final Creator<MovieViewModel> CREATOR = new Creator<MovieViewModel>() {
        @Override
        public MovieViewModel createFromParcel(Parcel source) {
            return new MovieViewModel(source);
        }

        @Override
        public MovieViewModel[] newArray(int size) {
            return new MovieViewModel[size];
        }
    };
}
