package com.infs3605.infs3605.APIEntities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field implements Serializable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = -5392008128446850697L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Field() {
    }

    /**
     *
     * @param id
     * @param type
     */
    public Field(String type, String id) {
        super();
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}