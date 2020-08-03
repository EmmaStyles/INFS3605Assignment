package com.infs3605.infs3605;

public class FavArticle {

    private String key_id;
//    private String item_industry;
//    private String item_segment;
    private String item_title;
    private String item_date;
//    private String item_content;

    public FavArticle(String key_id, String item_title, String item_date) {
        this.key_id = key_id;
        this.item_title = item_title;
        this.item_date = item_date;

    }

    public String getKey_id() {
        return key_id;
    }

    public String getItem_title() {
        return item_title;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

}
