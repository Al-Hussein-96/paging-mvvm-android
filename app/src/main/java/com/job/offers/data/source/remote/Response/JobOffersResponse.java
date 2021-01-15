package com.job.offers.data.source.remote.Response;

import com.job.offers.data.model.JobOffer;

import java.util.List;

public class JobOffersResponse {
    private int totalCount;
    private int totalPages;
    private List<JobOffer> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<JobOffer> getItems() {
        return items;
    }

    public void setItems(List<JobOffer> items) {
        this.items = items;
    }
}
