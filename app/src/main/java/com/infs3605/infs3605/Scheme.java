package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

public class Scheme implements Parcelable {

    String title;
    String dateCloses;
    String infoURL;

    public Scheme(){

    }
    public Scheme(String title, String dateCloses, String infoURL){
        this.title = title;
        this.dateCloses = dateCloses;
        this.infoURL = infoURL;
    }

    protected Scheme(Parcel in) {
        title = in.readString();
        dateCloses = in.readString();
        infoURL = in.readString();
    }

    public static final Creator<Scheme> CREATOR = new Creator<Scheme>() {
        @Override
        public Scheme createFromParcel(Parcel in) {
            return new Scheme(in);
        }

        @Override
        public Scheme[] newArray(int size) {
            return new Scheme[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDateCloses() {
        return dateCloses;
    }

    public String getInfoURL() {
        return infoURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateCloses(String dateCloses) {
        this.dateCloses = dateCloses;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(dateCloses);
        parcel.writeString(infoURL);
    }

    @Override
    public String toString() {
        return "Scheme{" +
                "title='" + title + '\'' +
                ", dateCloses='" + dateCloses + '\'' +
                ", infoURL='" + infoURL + '\'' +
                '}';
    }
}
