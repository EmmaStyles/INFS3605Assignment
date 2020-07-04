package com.infs3605.infs3605;

public class GeneralInfoArticle {
    String articleType;
    String generalInfoTitle;
    String generalInfoDate;
    String generalInfoContent;

    public GeneralInfoArticle(String articleType, String generalInfoTitle, String generalInfoDate, String generalInfoContent){
        this.generalInfoTitle = generalInfoTitle;
        this.generalInfoDate = generalInfoDate;
        this.generalInfoContent = generalInfoContent;
        this.articleType = articleType;
    }

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
}
