package com.gintong.demo.utils;

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
 * Created by lenovo on 2016/7/27.
 */
public class test2 {


     /**
      * 
      *
      * @Author: Frame
      * @Data:   2016/7/27 17:14
      * @Param:  
      * 
      */

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
                System.out.println("获取结果："+respBody.length());
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

        String saveCustomerProfile="http://192.168.130.130:81/cross/organ/customer/saveCustomerProfile.json";
        for(int i=1;i<1000;i++){


        JSONObject requestJson;
            requestJson = JSON.parseObject("{\"moudles\":[{\"moudleId\":2,\"isVisible\":\"1\",\"desc\":\"基本信息\",\"level\":0,\"controlList\":[{\"maxlength\":30,\"desc\":\"简称\",\"name\":\"shortName\",\"type\":\"text\",\"isMust\":true,\"value\":\"虚拟政府0"+i+"\"},{\"maxlength\":50,\"desc\":\"单位名称\",\"name\":\"name\",\"type\":\"text\",\"isMust\":false,\"value\":\"虚拟政府全称\"},{\"desc\":\"类型\",\"items\":[{\"name\":\"企业\",\"value\":1,\"checked\":true},{\"name\":\"政府/事业单位\",\"value\":2,\"checked\":false},{\"name\":\"媒体\",\"value\":3,\"checked\":false},{\"name\":\"其它\",\"value\":4,\"checked\":false}],\"name\":\"orgType\",\"type\":\"select\",\"isMust\":false,\"value\":\"\"},{\"desc\":\"所在地区\",\"name\":\"district\",\"type\":\"region\",\"isMust\":false,\"value\":{\"province\":\"北京\",\"city\":\"北京\",\"county\":\"东城区\"}},{\"desc\":\"所属行业\",\"name\":\"industry\",\"type\":\"industry\",\"isMust\":false,\"value\":{\"industryId\":\"1445\",\"industry\":\"政府/事业单位-工商\"}}],\"name\":\"basicInfo\",\"type\":\"single\"},{\"moudleId\":3,\"isVisible\":\"1\",\"desc\":\"单位简介\",\"level\":0,\"controlList\":[{\"desc\":\"单位简介\",\"name\":\"brief\",\"type\":\"editor\",\"isMust\":false,\"value\":\"<p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">原标题：中国共产党竟然打广告了！带你走进幕后故事</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">这两天，一条1分30秒的视频被广泛转载。</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">网友们纷纷点赞，并表示“看得心都融化了”。</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">还有网友惊讶地发现：“我党第一次打广告了”，“没想到有一天被我党广告圈粉”！</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">这则视频到底说了什么？视频背后又有哪些故事？小编为您揭秘——</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">央视公益广告《我是谁》</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\"><strong>《我是谁》温暖展现党员风采</strong></p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">95年风雨兼程铸就了今天的光辉岁月，95年坚守初心带领人民走向富强。为献礼七一建党，中央电视台特别推出公益广告《我是谁》，用温暖朴实的镜头语言传递出“我是中国共产党，我一直就在你身边”的创意理念。</p><p class=\\\"detailPic\\\" style=\\\"margin: 0px auto 10px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: center; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\"><img src=\\\"http://p2.ifengimg.com/haina/2016_31/62e7c1d4b664439_w640_h408.png\\\" alt=\\\"中国共产党竟然打广告了！带你走进幕后故事\\\" width=\\\"450\\\" height=\\\"287\\\" style=\\\"border: 0px; vertical-align: bottom; display: block; margin: 0px auto; max-width: 100%; height: auto;\\\"/></p><p class=\\\"picIntro\\\" style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; font-size: 14px; text-align: center; word-wrap: break-word; word-break: normal; font-family: 楷体_gb2312, 楷体; color: rgb(43, 43, 43); line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">央视公益广告《我是谁》海报</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">片子表现的是普通平凡的身边的人物故事，由六位平凡的党员代表来讲述，他们在各自不同岗位上兢兢业业、尽职尽责，默默奉献的身影平凡亦闪光。</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">通过镜头下他们的微笑、自信，展示出中国共产党员的风范，体现党和国家事业的新发展对党员的新要求：做讲政治、有信念，讲规矩、有纪律，讲道德、有品行，讲奉献、有作为的合格党员。</p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\"><strong>辗转三个城市 寻找最佳拍摄地</strong></p><p style=\\\"margin-top: 0px; margin-bottom: 25px; padding: 0px; text-indent: 28px; font-size: 14px; text-align: justify; word-wrap: break-word; word-break: normal; color: rgb(43, 43, 43); font-family: simsun, arial, helvetica, clean, sans-serif; line-height: 24px; white-space: normal; background-color: rgb(255, 255, 255);\\\">《我是谁》从创意到制作仅仅用了20天的时间，央视公益广告团队前期分别在<a href=\\\"http://app.travel.ifeng.com/city_detail_98\\\" target=\\\"_blank\\\" style=\\\"text-decoration: none; color: rgb(0, 66, 118); font-weight: bold;\\\">厦门</a>、武夷山和上海三地进行堪景选景，最终选定在上海进行了为期3天72小时的拍摄，为还原真实，拍摄团队精益求精，对医生、清洁工、交警等人物工作场景，反复进行多次试演，只为在观众面前呈现最高品质。</p><p><br/></p>\"}],\"name\":\"brief\",\"type\":\"single\"}],\"templateId\":2,\"lableList\":[{\"tagId\":\"12970\",\"tagName\":\"Bernard Baruch\"},{\"tagId\":\"31914\",\"tagName\":\"0513标签\"},{\"tagId\":\"31915\",\"tagName\":\"0513客户标签\"},{\"tagId\":\"34654\",\"tagName\":\"自定义0725\"},{\"tagId\":\"34656\",\"tagName\":\"北京大学\"},{\"tagId\":\"34657\",\"tagName\":\"金桐\"},{\"tagId\":\"12973\",\"tagName\":\"Medium-term Lending Facility\"},{\"tagId\":\"12969\",\"tagName\":\"Shelby Collum Davis\"}],\"directory\":[\"-1\",\"558\",\"557\",\"556\",\"555\"],\"relevance\":{\"p\":[{\"tag\":\"人脉\",\"conn\":[{\"career\":\"\",\"company\":\"\",\"id\":\"244841\",\"name\":\"张大桥\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"type\":\"2\",\"isOnline\":\"\",\"persontype\":\"2\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"name\":\"张大桥\",\"id\":\"244841\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"2\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"244860\",\"name\":\"公庆刚\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"type\":\"2\",\"isOnline\":\"\",\"persontype\":\"2\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"name\":\"公庆刚\",\"id\":\"244860\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"2\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"245515\",\"name\":\"宋丽婷\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/user/default.jpeg\",\"type\":\"2\",\"isOnline\":\"\",\"persontype\":\"2\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/user/default.jpeg\",\"name\":\"宋丽婷\",\"id\":\"245515\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"2\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"245589\",\"name\":\"陈向勇\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"type\":\"2\",\"isOnline\":\"\",\"persontype\":\"2\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"name\":\"陈向勇\",\"id\":\"245589\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"2\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"245306\",\"name\":\"何昕擘\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"type\":\"2\",\"isOnline\":\"\",\"persontype\":\"2\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/people/original/default.jpeg\",\"name\":\"何昕擘\",\"id\":\"245306\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"2\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"36\",\"name\":\"aa曲凤乐\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/90/1571584313697.jpg\",\"type\":\"1\",\"isOnline\":\"\",\"persontype\":\"1\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web1/90/1571584313697.jpg\",\"name\":\"aa曲凤乐\",\"id\":\"36\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"1\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"21\",\"name\":\"a陈永梅\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/pic/avatar/1552621191285.jpg\",\"type\":\"1\",\"isOnline\":\"\",\"persontype\":\"1\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web1/pic/avatar/1552621191285.jpg\",\"name\":\"a陈永梅\",\"id\":\"21\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"1\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"13604\",\"name\":\"1223\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/pic/avatar/140/16118141175011.jpg\",\"type\":\"1\",\"isOnline\":\"\",\"persontype\":\"1\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web1/pic/avatar/140/16118141175011.jpg\",\"name\":\"1223\",\"id\":\"13604\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"1\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"13959\",\"name\":\"闫晓琳\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web/pic/user/default.jpeg\",\"type\":\"1\",\"isOnline\":\"\",\"persontype\":\"1\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web/pic/user/default.jpeg\",\"name\":\"闫晓琳\",\"id\":\"13959\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"1\"}},{\"career\":\"\",\"company\":\"\",\"id\":\"13588\",\"name\":\"安之\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/90/14923134517492.jpg\",\"type\":\"1\",\"isOnline\":\"\",\"persontype\":\"1\",\"jtContactMini\":{\"isOnline\":\"\",\"image\":\"http://file.dev.gintong.com:81/web1/90/14923134517492.jpg\",\"name\":\"安之\",\"id\":\"13588\",\"company\":\"\",\"career\":\"\",\"isOffline\":\"\",\"persontype\":\"1\"}}]}],\"o\":[{\"tag\":\"组织\",\"conn\":[{\"id\":\"0\",\"name\":\"虚拟企业0101\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"fullName\":\"虚拟企业0101\",\"shortName\":\"虚拟企业0101\",\"id\":\"0\",\"organtype\":\"\"}},{\"id\":\"516839\",\"name\":\"普通用户创建客户\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"fullName\":\"普通用户创建客户\",\"shortName\":\"普通用户创建客户\",\"id\":\"516839\",\"organtype\":\"\"}},{\"id\":\"517950\",\"name\":\"0513web客户名称\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/140/16513161889938.png\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/140/16513161889938.png\",\"fullName\":\"0513web客户名称\",\"shortName\":\"0513web客户名称\",\"id\":\"517950\",\"organtype\":\"\"}},{\"id\":\"517280\",\"name\":\"11\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/140/16122151956504.png\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/140/16122151956504.png\",\"fullName\":\"11\",\"shortName\":\"11\",\"id\":\"517280\",\"organtype\":\"\"}},{\"id\":\"517491\",\"name\":\"160304创建客户\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/140/16513161052706.png\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/140/16513161052706.png\",\"fullName\":\"160304创建客户\",\"shortName\":\"160304创建客户\",\"id\":\"517491\",\"organtype\":\"\"}},{\"id\":\"518022\",\"name\":\"发轮功\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"fullName\":\"发轮功\",\"shortName\":\"发轮功\",\"id\":\"518022\",\"organtype\":\"\"}},{\"id\":\"554\",\"name\":\"在我的\",\"ownerId\":\"\",\"ownerName\":\"\",\"picPath\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"isOnline\":\"\",\"organtype\":\"\",\"organizationMini\":{\"isOnline\":\"\",\"logo\":\"http://file.dev.gintong.com:81/web1/organ/avatar/default.jpeg\",\"fullName\":\"在我的\",\"shortName\":\"在我的\",\"id\":\"554\",\"organtype\":\"\"}}]}],\"k\":[{\"tag\":\"知识\",\"conn\":[{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"13158439\",\"title\":\"数据证书豪为联盟最佳队友 阿德为己应投湖人\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"5\",\"type\":\"5\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"13158415\",\"title\":\"李迅雷：A股不是被经济牵着的那条狗\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"1\",\"type\":\"1\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"13158446\",\"title\":\"许巍新歌发布 高晓松谈合作契机:年少时很熟\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"5\",\"type\":\"5\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"13158427\",\"title\":\"Zootopia\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"5\",\"type\":\"5\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"13158410\",\"title\":\"江苏黑校车塞进28名幼儿 幼儿园长及司机获刑\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"5\",\"type\":\"5\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"2972402\",\"title\":\"\u200B\u200B\u200B\u200B\u200B哈哈\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"1\",\"type\":\"1\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"2971873\",\"title\":\"习近平登上黑瞎子岛 察看开发规划\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"1\",\"type\":\"1\"},{\"columnpath\":\"\",\"columntype\":\"\",\"id\":\"2972397\",\"title\":\"印尼总统亲赴纳土纳群岛 登上射击中国渔船军舰\",\"ownerId\":\"\",\"ownerName\":\"\",\"knowtype\":\"1\",\"type\":\"1\"}]}],\"r\":[{\"tag\":\"事件\",\"conn\":[{\"id\":\"29192\",\"title\":\"北京市密云县城国有土地使用权转让项目（做住宅一级开发）\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"29332\",\"title\":\"测试需求附件打开\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"67755\",\"title\":\"rongzi\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"2147483647\",\"title\":\"0401创建项目验证确认合作\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"3\",\"title\":\"艰难困苦\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"2\",\"title\":\"版本了\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"9\",\"title\":\"XP你咯个你还信行吗\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"3585\",\"title\":\"yyyh\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"},{\"id\":\"1242\",\"title\":\"iip\",\"ownerId\":\"\",\"ownerName\":\"\",\"requirementtype\":\"\",\"demandtype\":\"\",\"type\":\"1\"}]}]},\"customerPermissions\":{\"connectFlag\":\"1\",\"shareFlag\":\"1\",\"publicFlag\":\"1\"}}");
            HttpConfig httpconfig2=HttpConfig.custom().url(saveCustomerProfile).method(HttpMethods.POST).jsonobj(requestJson).handler(handler).hostOrIP("127.0.0.1").port(8888).headers(HttpHeader.custom().keepAlive("true").s("web").sessionID(responseDataJson.getString("sessionId")).build());

//        String resbody2= HttpClientUtil.send(httpconfig2);
            HttpAsyncClientUtil.send(httpconfig2);
        System.out.println("创建第"+i+"个 客户 resbody:");

        }






    }
}
