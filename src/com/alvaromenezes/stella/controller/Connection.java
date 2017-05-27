package com.alvaromenezes.stella.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class Connection {


    public String getUrlData(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();


    }


}
