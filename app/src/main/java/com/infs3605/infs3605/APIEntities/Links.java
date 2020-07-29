package com.infs3605.infs3605.APIEntities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Serializable
{

    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("next")
    @Expose
    private String next;
    private final static long serialVersionUID = 1194045266937243245L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Links() {
    }

    /**
     *
     * @param next
     * @param start
     */
    public Links(String start, String next) {
        super();
        this.start = start;
        this.next = next;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}