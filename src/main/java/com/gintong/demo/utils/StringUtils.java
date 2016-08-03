package com.gintong.demo.utils;

/**
 * Created by lenovo on 2016/8/3.
 */
public class StringUtils {
    
    
     /**
      * String 转换成String[]
      *
      * @Author: Frame
      * @Data:   2016/8/3 16:13
      * @Param:  
      * 
      */
    public static String[] StringSplitByComma( String str){

        if(!str.isEmpty()){
            return str.split(",");
        }
        else {
            return null;
        }
    }
    
}
