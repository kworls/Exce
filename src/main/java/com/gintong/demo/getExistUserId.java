package com.gintong.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gintong.demo.utils.FileUtils;
import com.gintong.exception.HttpProcessException;
import com.gintong.httpclient.HttpAsyncClientUtil;
import com.gintong.httpclient.HttpClientUtil;
import com.gintong.utils.HttpConfig;
import com.gintong.utils.HttpHeader;
import com.gintong.utils.HttpMethods;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 /**
  * 获取线上存在的用户id
  *
  * @Author: Frame
  * @Data:   2016/8/3 17:48
  * @Param:  
  * 
  */
public class getExistUserId {
     public static void main(String[] args) throws HttpProcessException {

         String url="http://www.gintong.com/cross/web/login.json";
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
                 if(!notifCode.equals("9999")){
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



         String kickFromMUC="http://www.gintong.com/crossimserver/mobile/im/kickFromMUC.action";

         List l=new ArrayList();

         for(int i=10000;i<9999999;i++){
             JSONObject requestJson;

             requestJson = JSON.parseObject("{mucID:\"152383\",userID:"+i+"}");
             HttpConfig httpconfig2=HttpConfig.custom().url(kickFromMUC).method(HttpMethods.POST).jsonobj(requestJson).handler(handler).headers(HttpHeader.custom().keepAlive("true").s("web").sessionID(responseDataJson.getString("sessionId")).build());

             String res=HttpClientUtil.send(httpconfig2);
             JSONObject resJson2= JSON.parseObject(res);
             JSONObject responseDataJsonnotification=JSON.parseObject(resJson2.getString("notification"));

             String notifCode=responseDataJsonnotification.getString("notifCode");
             if(!notifCode.equals("9999")){
                 l.add(i);
                 System.out.println(i);
             }

         }
         try {
             FileUtils.createNewFile(".//data//id.txt",l.toString());
         } catch (IOException e) {
             e.printStackTrace();
         }


     }

}
