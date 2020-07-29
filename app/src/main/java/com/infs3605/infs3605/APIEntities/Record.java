package com.infs3605.infs3605.APIEntities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record implements Serializable
{
    @SerializedName("notification_date")
    @Expose
    private String notificationDate;
    @SerializedName("postcode")
    @Expose
    private int postcode;
    @SerializedName("lhd_2010_code")
    @Expose
    private String lhd2010Code;
    @SerializedName("lhd_2010_name")
    @Expose
    private String lhd2010Name;
    @SerializedName("lga_code19")
    @Expose
    private int lgaCode19;
    @SerializedName("lga_name19")
    @Expose
    private String lgaName19;
    private final static long serialVersionUID = -5898929964576240662L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Record() {
    }

    /**
     *
     * @param lgaName19
     * @param lhd2010Code
     * @param postcode
     * @param lhd2010Name
     * @param notificationDate
     * @param lgaCode19
     */
    public Record(String notificationDate, int postcode, String lhd2010Code, String lhd2010Name, int lgaCode19, String lgaName19) {
        super();
        this.notificationDate = notificationDate;
        this.postcode = postcode;
        this.lhd2010Code = lhd2010Code;
        this.lhd2010Name = lhd2010Name;
        this.lgaCode19 = lgaCode19;
        this.lgaName19 = lgaName19;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getLhd2010Code() {
        return lhd2010Code;
    }

    public void setLhd2010Code(String lhd2010Code) {
        this.lhd2010Code = lhd2010Code;
    }

    public String getLhd2010Name() {
        return lhd2010Name;
    }

    public void setLhd2010Name(String lhd2010Name) {
        this.lhd2010Name = lhd2010Name;
    }

    public int getLgaCode19() {
        return lgaCode19;
    }

    public void setLgaCode19(int lgaCode19) {
        this.lgaCode19 = lgaCode19;
    }

    public String getLgaName19() {
        return lgaName19;
    }

    public void setLgaName19(String lgaName19) {
        this.lgaName19 = lgaName19;
    }

}