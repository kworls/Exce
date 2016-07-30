package com.gintong.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gintong.exception.HttpProcessException;
import com.gintong.httpclient.HttpAsyncClientUtil;
import com.gintong.httpclient.HttpClientUtil;
import com.gintong.utils.HttpConfig;
import com.gintong.utils.HttpHeader;
import com.gintong.utils.HttpMethods;
import org.apache.http.nio.client.HttpAsyncClient;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/7/19.
 */

public class test {


     /**
      *
      *
      * @Author: Frame
      * @Data:   2016/7/19 16:22
      * @Param:
      *
      */

    public static void main(String[] args) throws HttpProcessException {


        String url="http://jtmobile.gintong.com:4445//login/loginConfiguration.json";
        Map<String, Object> parammap=new HashMap<String,Object>();
        String body;

        parammap.put("clientID", "");
        parammap.put("clientPassword", "");
        parammap.put("imei", "866769028859885");
        parammap.put("version", "1.1.1 beta");
        parammap.put("platform", "");
        parammap.put("model", "");
        parammap.put("resolution", "");
        parammap.put("systemName", "android");
        parammap.put("systemVersion", "22");
        parammap.put("channelID", "");
        parammap.put("loginString", "18664900944");
        parammap.put("password", "MTIxMjEy");






        HttpAsyncClientUtil.IHandler handler = new HttpAsyncClientUtil.IHandler() {
            String body="";
            @Override
            public Object failed(Exception e) {
                System.out.println("失败了");
                return null;
            }

            @Override
            public Object completed(String respBody) {
                System.out.println("获取结果："+respBody.length());
                return body=respBody;
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


        String jsonString= JSONObject.toJSONString(parammap);
        System.out.println(jsonString);
//        HttpAsyncClientUtil.create(url).
        HttpConfig httpconfig=HttpConfig.custom().url(url).method(HttpMethods.POST).map(parammap).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").build());

        HttpAsyncClientUtil hac=new HttpAsyncClientUtil();
        hac.send(httpconfig);


        String resbody=HttpClientUtil.send(httpconfig);
        System.out.println("resbody:"+resbody);

        JSONObject resJson= JSON.parseObject(resbody);
        JSONObject responseDataJson=JSON.parseObject(resJson.getString("responseData"));
        JSONObject jtMemberJson=JSON.parseObject(responseDataJson.getString("jtMember"));
        JSONObject personJson=JSON.parseObject(jtMemberJson.getString("person"));


        JSONObject userInfoJson=new JSONObject();
        userInfoJson.put("sessionID", responseDataJson.getString("sessionID"));
        userInfoJson.put("id", jtMemberJson.getString("id"));
        userInfoJson.put("name", personJson.getString("name"));
        System.out.println("sessionID:"+userInfoJson.getString("sessionID"));







    }

}
