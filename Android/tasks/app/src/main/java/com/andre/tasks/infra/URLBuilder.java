package com.andre.tasks.infra;

public class URLBuilder {


    private String url;

    public URLBuilder(String mainUrl){
        this.url = mainUrl;
    }

    public void addResource(String value) {
        this.url += "/" + value;
    }

    public String getUrl() {
        return this.url;
    }
}
