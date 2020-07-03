package com.infs3605.infs3605;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class IndustryClass implements Parcelable {
    public String industryName;
    public ArrayList<String> industrySegments;

    protected IndustryClass(Parcel in) {
        industryName = in.readString();
        industrySegments = in.createStringArrayList();
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

    public IndustryClass(String industryName, ArrayList<String> industrySegments){
        this.industryName = industryName;
        this.industrySegments = industrySegments;
    }

    public void setIndustrySegments(ArrayList<String> industrySegments) {
        this.industrySegments = industrySegments;
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
    }

}
