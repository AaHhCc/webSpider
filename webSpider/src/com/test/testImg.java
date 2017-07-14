package com.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 测试git
 * Created by DELL on 2017/7/14.
 */
public class testImg {
    public void getImg(String strUrl,String name){
        try {
            URL url=new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream is = httpURLConnection.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            //读取图片数据
            while((len=is.read(buf))!=-1){
                outStream.write(buf,0,len);
            }
            is.close();
            outStream.close();
            //把图片数据填入文件中
            String path = "e://img/";
            File f = new File(path);
            if(!f.isDirectory()){
                f.mkdir();
            }
            File file = new File(path+name+".jpg");

            FileOutputStream op = new FileOutputStream(file);

            op.write(outStream.toByteArray());

            op.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
