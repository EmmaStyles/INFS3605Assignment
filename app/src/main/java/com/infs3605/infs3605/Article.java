package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    String industry;
    String segment;
    String title;
    String date;
    String content;

    public Article(String industry, String segment, String title, String date, String content){
        this.industry = industry;
        this.segment = segment;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    protected Article(Parcel in) {
        industry = in.readString();
        segment = in.readString();
        title = in.readString();
        date = in.readString();
        content = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getIndustry(){
        return industry;
    }

    public String getSegment() {
        return segment;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setIndustry(String industry){
        this.industry = industry;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(industry);
        parcel.writeString(segment);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(content);
    }
}
