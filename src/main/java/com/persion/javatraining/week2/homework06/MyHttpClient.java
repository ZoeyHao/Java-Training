package com.persion.javatraining.week2.homework06;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MyHttpClient {
    // 缓存客户端实例
    public OkHttpClient client =null;

    public MyHttpClient() {
        client=new OkHttpClient();
    }
    // GET 调用
    public String getAsString(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public void main() {
        try {
            String url = "https://http://localhost:8801";
            String text =  getAsString(url);
            System.out.println("url: " + url + " ; response: \n" + text);
        }catch (IOException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();;
        }finally {
            // 清理资源
            client = null;
        }
    }
}
