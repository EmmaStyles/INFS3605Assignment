package com.infs3605.infs3605.APIEntities;

import java.io.Serializable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class RecordsResponse implements Serializable
{

    @SerializedName("help")
    @Expose
    private String help;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("result")
    @Expose
    private Result result;
    private final static long serialVersionUID = -8555453303405119112L;

    /**
     * No args constructor for use in serialization
     *
     */
    public RecordsResponse() {
    }

    /**
     *
     * @param result
     * @param help
     * @param success
     */
    public RecordsResponse(String help, boolean success, Result result) {
        super();
        this.help = help;
        this.success = success;
        this.result = result;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}