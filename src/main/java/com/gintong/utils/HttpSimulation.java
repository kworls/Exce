package com.gintong.utils;


/**
 * Created by lenovo on 2016/7/14.
 */
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.HashMap;


public class HttpSimulation {


    private boolean IsProxy =false;
    private long startTime = 0L;





    public  void  doHttpGet(HashMap<String, String> headers, String getRequestUrl){

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(getRequestUrl);
        ArrayList<Header> header = new ArrayList<Header>();




    }

    public  void  doHttpPost(){

    }

    public boolean isProxy() {
        return IsProxy;
    }

    public void setProxy(boolean proxy) {
        IsProxy = proxy;
    }
}
