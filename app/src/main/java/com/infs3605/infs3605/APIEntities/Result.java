package com.infs3605.infs3605.APIEntities;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("include_total")
    @Expose
    private boolean includeTotal;
    @SerializedName("resource_id")
    @Expose
    private String resourceId;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;
    @SerializedName("records_format")
    @Expose
    private String recordsFormat;
    @SerializedName("records")
    @Expose
    private List<Record> records = null;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("total")
    @Expose
    private int total;
    private final static long serialVersionUID = 6916993255457170179L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param includeTotal
     * @param recordsFormat
     * @param resourceId
     * @param total
     * @param records
     * @param limit
     * @param links
     * @param fields
     */
    public Result(boolean includeTotal, String resourceId, List<Field> fields, String recordsFormat, List<Record> records, int limit, Links links, int total) {
        super();
        this.includeTotal = includeTotal;
        this.resourceId = resourceId;
        this.fields = fields;
        this.recordsFormat = recordsFormat;
        this.records = records;
        this.limit = limit;
        this.links = links;
        this.total = total;
    }

    public boolean isIncludeTotal() {
        return includeTotal;
    }

    public void setIncludeTotal(boolean includeTotal) {
        this.includeTotal = includeTotal;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getRecordsFormat() {
        return recordsFormat;
    }

    public void setRecordsFormat(String recordsFormat) {
        this.recordsFormat = recordsFormat;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}