package lk.isuru781.reportmodule.dto;

public class ReportRequest {
    private String query;

    public ReportRequest() {
    }

    public ReportRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

