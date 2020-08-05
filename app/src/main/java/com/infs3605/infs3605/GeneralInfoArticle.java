package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

public class GeneralInfoArticle implements Parcelable {
    String articleID;
    String articleType;
    String generalInfoTitle;
    String generalInfoDate;
    String generalInfoContent;
    String infoStatus;

    public GeneralInfoArticle(String articleID, String articleType, String generalInfoTitle, String generalInfoDate, String generalInfoContent, String infoStatus){
        this.articleID = articleID;
        this.generalInfoTitle = generalInfoTitle;
        this.generalInfoDate = generalInfoDate;
        this.generalInfoContent = generalInfoContent;
        this.articleType = articleType;
        this.infoStatus = infoStatus;
    }

    protected GeneralInfoArticle(Parcel in) {
        articleID = in.readString();
        articleType = in.readString();
        generalInfoTitle = in.readString();
        generalInfoDate = in.readString();
        generalInfoContent = in.readString();
        infoStatus = in.readString();
    }

    public static final Creator<GeneralInfoArticle> CREATOR = new Creator<GeneralInfoArticle>() {
        @Override
        public GeneralInfoArticle createFromParcel(Parcel in) {
            return new GeneralInfoArticle(in);
        }

        @Override
        public GeneralInfoArticle[] newArray(int size) {
            return new GeneralInfoArticle[size];
        }
    };

    public String getArticleType() {
        return articleType;
    }

    public String getGeneralInfoTitle() {
        return generalInfoTitle;
    }

    public String getGeneralInfoDate() {
        return generalInfoDate;
    }

    public String getGeneralInfoContent() {
        return generalInfoContent;
    }

    public void setArticleType(String articleType){
        this.articleType = articleType;
    }

    public void setGeneralInfoTitle(String generalInfoTitle) {
        this.generalInfoTitle = generalInfoTitle;
    }

    public void setGeneralInfoDate(String generalInfoDate) {
        this.generalInfoDate = generalInfoDate;
    }

    public void setGeneralInfoContent(String generalInfoContent) {
        this.generalInfoContent = generalInfoContent;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(articleID);
        parcel.writeString(articleType);
        parcel.writeString(generalInfoTitle);
        parcel.writeString(generalInfoDate);
        parcel.writeString(generalInfoContent);
        parcel.writeString(infoStatus);
    }


}
