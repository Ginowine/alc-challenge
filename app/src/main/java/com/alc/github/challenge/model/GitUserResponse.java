package com.alc.github.challenge.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gino Osahon on 20/04/2017.
 */
public class GitUserResponse {

    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<GitUser> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GitUser> getItems() {
        return items;
    }

    public void setItems(List<GitUser> items) {
        this.items = items;
    }

}
