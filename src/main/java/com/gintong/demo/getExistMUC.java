package com.gintong.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gintong.exception.HttpProcessException;
import com.gintong.httpclient.HttpAsyncClientUtil;
import com.gintong.httpclient.HttpClientUtil;
import com.gintong.utils.HttpConfig;
import com.gintong.utils.HttpHeader;
import com.gintong.utils.HttpMethods;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/8/1.
 */
public class getExistMUC {
    public static void main(String[] args) throws HttpProcessException {


        String url="http://192.168.130.130:81/cross/web/login.json";
        Map<String, Object> parammap=new HashMap<String,Object>();
        String body;

        parammap.put("username", "18664900944");
        parammap.put("password", "121212");
        parammap.put("vCode", "");
        parammap.put("index", "0");


        HttpAsyncClientUtil.IHandler handler = new HttpAsyncClientUtil.IHandler() {

            @Override
            public Object failed(Exception e) {
                System.out.println("失败了");
                return null;
            }

            @Override
            public Object completed(String respBody) {
                JSONObject resJson= JSON.parseObject(respBody);
                JSONObject responseDataJsonnotification=JSON.parseObject(resJson.getString("notification"));

                String notifCode=responseDataJsonnotification.getString("notifCode");
                if(notifCode.equals("1412")){
                    System.out.println(resJson.getJSONObject("responseData").getJSONObject("mucDetail").getString("id"));
                }


                return null;
            }

            @Override
            public Object cancelled() {
                System.out.println("取消了");
                return null;
            }

            @Override
            public Object down(OutputStream out) {
                System.out.println("开始下载");
                return null;
            }
        };



        HttpConfig httpconfig=HttpConfig.custom().url(url).method(HttpMethods.POST).map(parammap).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").build());

        String resbody= HttpClientUtil.send(httpconfig);

        JSONObject resJson= JSON.parseObject(resbody);
        JSONObject responseDataJson=JSON.parseObject(resJson.getString("responseData"));



        JSONObject userInfoJson=new JSONObject();
        userInfoJson.put("sessionID", responseDataJson.getString("sessionId"));


        //获取未解散的MUCID

        String saveCustomerProfile="http://test.online.gintong.com/crossimserver/mobile/im/exitFromMUC.action";
        for(int i=0;i<13200;i++){

            JSONObject requestJson;
            requestJson = JSON.parseObject("{\"mucID\":"+i+",\"userID\":11}");
            HttpConfig httpconfig2=HttpConfig.custom().url(saveCustomerProfile).method(HttpMethods.POST).jsonobj(requestJson).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").s("web").sessionID(responseDataJson.getString("sessionId")).build());

            String res=HttpClientUtil.send(httpconfig2);
            JSONObject resJson2= JSON.parseObject(res);
            JSONObject responseDataJsonnotification=JSON.parseObject(resJson2.getString("notification"));

            String notifCode=responseDataJsonnotification.getString("notifCode");
            if(!notifCode.equals("1411")){
                System.out.println(i);
            }

        }






    }
}
