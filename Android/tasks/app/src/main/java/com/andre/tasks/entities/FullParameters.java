package com.andre.tasks.entities;

import java.util.AbstractMap;
import java.util.HashMap;

public class FullParameters {

    public String method;
    public String url;
    public AbstractMap<String,String> headerParameters;
    public AbstractMap<String,String> parameters;

    public FullParameters(String method, String url, HashMap headerParameters, HashMap params) {
        this.method = method;
        this.url = url;
        this.headerParameters = headerParameters;
        this.parameters = params;
    }
}
