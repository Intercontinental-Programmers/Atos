package com.ip.data.client;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpResponse {

    private int status;
    private JSONArray body;

    public HttpResponse(int status, JSONArray body) {
        this.status = status;
        this.body = body;
    }


    public int getStatus() {
        return status;
    }

    public JSONArray getBody() {
        return body;
    }
}
