package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    String keyId;
    String industry;
    String segment;
    String title;
    String date;
    String content;
    String favStatus;

//    String articleImageUrl;

    public Article(String keyId, String industry, String segment, String title, String date, String content, String favStatus){
        this.keyId = keyId;
        this.industry = industry;
        this.segment = segment;
        this.title = title;
        this.date = date;
        this.content = content;
//        this.articleImageUrl = articleImageUrl;
        this.favStatus = favStatus;
    }

    protected Article(Parcel in) {
        keyId = in.readString();
        industry = in.readString();
        segment = in.readString();
        title = in.readString();
        date = in.readString();
        content = in.readString();
//        articleImageUrl = in.readString();
        favStatus = in.readString();
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

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
    //    public void setArticleImageUrl(String articleImageUrl) {
//        this.articleImageUrl = articleImageUrl;
//    }
//
//    public String getArticleImageUrl() {
//        return articleImageUrl;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyId);
        parcel.writeString(industry);
        parcel.writeString(segment);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(content);
        parcel.writeString(favStatus);
//        parcel.writeString(articleImageUrl);
    }
}
