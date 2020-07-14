package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class IndustryClass implements Parcelable {
    public String industryName;
    public ArrayList<String> industrySegments;
    public String imageUrl;

    protected IndustryClass(Parcel in) {
        industryName = in.readString();
        industrySegments = in.createStringArrayList();
        imageUrl = in.readString();
    }

    public static final Creator<IndustryClass> CREATOR = new Creator<IndustryClass>() {
        @Override
        public IndustryClass createFromParcel(Parcel in) {
            return new IndustryClass(in);
        }

        @Override
        public IndustryClass[] newArray(int size) {
            return new IndustryClass[size];
        }
    };

    public ArrayList<String> getIndustrySegments() {
        return industrySegments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public IndustryClass(String industryName, ArrayList<String> industrySegments, String imageUrl){
        this.industryName = industryName;
        this.industrySegments = industrySegments;
        this.imageUrl = imageUrl;
    }

    public void setIndustrySegments(ArrayList<String> industrySegments) {
        this.industrySegments = industrySegments;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(industryName);
        parcel.writeStringList(industrySegments);
        parcel.writeString(imageUrl);
    }

}
