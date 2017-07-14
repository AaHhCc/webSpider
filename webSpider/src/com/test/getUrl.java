package com.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取CSDN首页发布文章的前10页用户的头像
 * Created by DELL on 2017/7/14.
 */
public class getUrl {
    public static void main(String[] args) {
        URL url=null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
//        String regex = "http://static.699pic.com/index_banner/[\\w+\\.?/?]+\\.[A-Za-z]+";
        //http://avatar.csdn.net/E/C/E/1_u014571011.jpg
        String regex = "http://avatar.csdn.net/[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);
        List<String> listUrl = new ArrayList<String>();
        int i=1;
        while (i<2){
            try {
                url=new URL("http://blog.csdn.net/"+"?&page="+i);
                System.out.println(url);
                i++;
                //http://img95.699pic.com/photo
//            ` url = new URL("http://www.sina.com.cn/");
                urlconn = url.openConnection();
                pw = new PrintWriter(new FileWriter("e:/url.txt"), true);
                br = new BufferedReader(new InputStreamReader(
                        urlconn.getInputStream()));
                String buf = null;
                while ((buf = br.readLine()) != null) {
                    Matcher buf_m = p.matcher(buf);
                    while (buf_m.find()) {
                        String t = buf_m.group();
//                        System.out.println("url="+t);
                        listUrl.add(t);
                        pw.println(buf_m.group());
                    }
                }
                System.out.println("获取成功！");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        testImg t = new testImg();
        for(String u : listUrl){
            String name =u.substring(u.lastIndexOf("_")+1,u.lastIndexOf("."));
//            System.out.println("name="+name);
            t.getImg(u,name);
        }
    }
}
