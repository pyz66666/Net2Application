package com.example.pyz.net2application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoadJson(View view) {
      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  /*URL url =new URL("https://192.168.3.8:9102/get/text");*/
                  URL url =new URL("http://www.sunofbeaches.com/shop/api/discovery/categories");
                  HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                  httpURLConnection.setConnectTimeout(10000);
                  httpURLConnection.setRequestMethod("GET");
                  httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
                  httpURLConnection.setRequestProperty("Accept","*/*");
                  httpURLConnection.connect();
                  //结果码
                  int responseCode = httpURLConnection.getResponseCode();
                  if(responseCode==200){
                      Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                      Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
                      for (Map.Entry<String, List<String>> entry : entries) {
                          Log.d(TAG,entry.getKey()+" == "+entry.getValue());
                      }
                      Object content = httpURLConnection.getContent();
                      Log.d(TAG,"content ---->"+content);
                  }
              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }).start();

    }
}
