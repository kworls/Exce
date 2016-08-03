package com.gintong.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gintong.demo.utils.FileUtils;
import com.gintong.demo.utils.StringUtils;
import com.gintong.exception.HttpProcessException;
import com.gintong.httpclient.HttpAsyncClientUtil;
import com.gintong.httpclient.HttpClientUtil;
import com.gintong.utils.HttpConfig;
import com.gintong.utils.HttpHeader;
import com.gintong.utils.HttpMethods;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

 /**
  *
  *
  * @Author: Frame
  * @Data:   2016/8/1 17:19
  * @Param:
  *
  */
public class exitFromMUC {
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
                if(notifCode.equals("0000")){
                    System.out.println("已退出畅聊，id:"+resJson.getJSONObject("responseData").getJSONObject("mucDetail").getString("id"));
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


//        String jsonString= JSONObject.toJSONString(parammap);
//        System.out.println(jsonString);
//        HttpAsyncClientUtil.create(url).
        HttpConfig httpconfig=HttpConfig.custom().url(url).method(HttpMethods.POST).map(parammap).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").build());

//        HttpAsyncClientUtil hac=new HttpAsyncClientUtil();
//        hac.send(httpconfig);


        String resbody= HttpClientUtil.send(httpconfig);
//        System.out.println("resbody:"+resbody);

        JSONObject resJson= JSON.parseObject(resbody);
        JSONObject responseDataJson=JSON.parseObject(resJson.getString("responseData"));
//        JSONObject jtMemberJson=JSON.parseObject(responseDataJson.getString("jtMember"));
//        JSONObject personJson=JSON.parseObject(jtMemberJson.getString("person"));


        JSONObject userInfoJson=new JSONObject();
        userInfoJson.put("sessionID", responseDataJson.getString("sessionId"));

//        System.out.println("sessionID:"+userInfoJson.getString("sessionID"));



        //创建客户

        String saveCustomerProfile="http://test.online.gintong.com/crossimserver/mobile/im/exitFromMUC.action";

//        for(int i=0;i<2501;i++){
//
//
//            JSONObject requestJson;
////            requestJson = JSON.parseObject("{\"mucID\":"+i+",\"userID\":200180}");
//            requestJson = JSON.parseObject("{\"mucID\":"+i+",\"userID\":11}");
//            HttpConfig httpconfig2=HttpConfig.custom().url(saveCustomerProfile).method(HttpMethods.POST).jsonobj(requestJson).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").s("web").sessionID(responseDataJson.getString("sessionId")).build());
//
////        String resbody2= HttpClientUtil.send(httpconfig2);
//            HttpAsyncClientUtil.send(httpconfig2);
//
//
//        }




        String FilePath=".//data//existmuc.txt";
        File file =new File(FilePath);
        String str="";
        String[] strlist=null;
        try {
            System.out.println(FileUtils.getFileSize(file));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            str=FileUtils.readFile(FilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        strlist= StringUtils.StringSplitByComma(str);

        for(String s:strlist){
            JSONObject requestJson;
//            requestJson = JSON.parseObject("{\"mucID\":"+i+",\"userID\":200180}");
            requestJson = JSON.parseObject("{\"mucID\":"+s+",\"userID\":11}");
            HttpConfig httpconfig2=HttpConfig.custom().url(saveCustomerProfile).method(HttpMethods.POST).jsonobj(requestJson).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").s("web").sessionID(responseDataJson.getString("sessionId")).build());

//        String resbody2= HttpClientUtil.send(httpconfig2);
            HttpAsyncClientUtil.send(httpconfig2);
        }








    }
     public String[] getStringArray(String FilePath){

//         String FilePath=".//data//existmuc.txt";
         File file =new File(FilePath);
         String str="";

         try {
             System.out.println(FileUtils.getFileSize(file));

         } catch (IOException e) {
             e.printStackTrace();
         }

         try {
             str=FileUtils.readFile(FilePath);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return StringUtils.StringSplitByComma(str);

     }

 }
