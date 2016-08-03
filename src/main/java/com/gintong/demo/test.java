package com.gintong.demo;

/**
 * Created by lenovo on 2016/7/19.
 */
import com.gintong.demo.utils.FileUtils;
import com.gintong.demo.utils.StringUtils;

import java.io.File;
import java.io.IOException;

public class test {


     /**
      *
      *
      * @Author: Frame
      * @Data:   2016/7/19 16:22
      * @Param:
      *
      */

    public static void main(String[] args)  {
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
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        strlist= StringUtils.StringSplitByComma(str);
        for(String s:strlist){
            System.out.println(s);
        }



    }

}
