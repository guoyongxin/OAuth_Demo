package com.example.oauth2;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sinaBtn = (Button) findViewById(R.id.sinaBtn);
        sinaBtn.setOnClickListener(this);
        sinaBtn.setTag(ConfigUtil.SINAW);
        Button qqBtn = (Button) findViewById(R.id.qqBtn);
        qqBtn.setOnClickListener(this);
        qqBtn.setTag(ConfigUtil.QQW);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        ConfigUtil conf = ConfigUtil.getInstance();
        String curWeibo = String.valueOf(v.getTag());
        conf.setCurWeibo(curWeibo);
        if (conf.getCurWeibo().equals(ConfigUtil.QQW))
        {
            conf.initQQ();
        }else if(conf.getCurWeibo().equals(ConfigUtil.SINAW))
        {
            conf.initSina();
        }
        
        String authUrl = OAuth2.getInstance().getAuthURL();
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        intent.putExtra("URL", authUrl);
        startActivity(intent);
    }
}
