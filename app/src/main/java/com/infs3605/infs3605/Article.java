package com.infs3605.infs3605;

public class Article {
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
}
