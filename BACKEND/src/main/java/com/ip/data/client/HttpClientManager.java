package com.ip.data.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClientManager {

    public HttpResponse get(String url) throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        JSONObject body = extractBody(response);
        int statusCode = response.getStatusLine().getStatusCode();

        response.close();
        client.close();
        return new HttpResponse(statusCode, body);
    }

    private JSONObject extractBody(CloseableHttpResponse response) throws IOException {


        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return new JSONObject(result.toString());
    }
}
