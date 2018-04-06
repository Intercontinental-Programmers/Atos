package com.ip.data.client;

import org.json.JSONObject;

public class HttpResponse {

    private int status;
    private JSONObject body;

    public HttpResponse(int status, JSONObject body) {
        this.status = status;
        this.body = body;
    }


    public int getStatus() {
        return status;
    }

    public JSONObject getBody() {
        return body;
    }
}
