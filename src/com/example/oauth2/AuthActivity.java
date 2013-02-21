/*
 * Copyright (c) 2012 NeuLion, Inc. All Rights Reserved.
 */
package com.example.oauth2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;


public class AuthActivity extends Activity
{
    Handler myHandler;
    ConfigUtil mConfigUtil = ConfigUtil.getInstance();
    OAuth2 mOAuth2 = OAuth2.getInstance();
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_ui);
        String url = OAuth2.getInstance().getAuthURL();
        mBtn = (Button) findViewById(R.id.btn_send);
        mBtn.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                if (mConfigUtil.getCurWeibo().endsWith(ConfigUtil.QQW))
                {
                    requestAPI("https://graph.qq.com/user/get_user_info?");
                }
                else if (mConfigUtil.getCurWeibo().endsWith(ConfigUtil.SINAW))
                {
                    requestAPI("https://api.weibo.com/2/statuses/public_timeline.json?");
                }
            }
        });
        myHandler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                case 1001://QQ get Open ID by HTML content
                    String content = (String) msg.obj;
                    OAuth2.getInstance().setOpenIdAndClientId(content);
                    Log.i("1001", content);
                    // may update UI
                    break;
                case 1002://get requestAPI content
                    String infomation = (String) msg.obj;
                    Log.i("1002", infomation);
                    break;
                }
                super.handleMessage(msg);
            }
        };
        initWebView(url);
    }

    private void initWebView(String url)
    {
        WebView authorizationView = (WebView) findViewById(R.id.authorizationView);
        authorizationView.clearCache(true);
        authorizationView.getSettings().setJavaScriptEnabled(true);
        authorizationView.getSettings().setSupportZoom(true);
        authorizationView.getSettings().setBuiltInZoomControls(true);
        authorizationView.setWebViewClient(new WebViewOauth(myHandler));
        authorizationView.loadUrl(url);
    }


    public void requestAPI(String baseUrl)
    {
        if (mConfigUtil.getCurWeibo().equals(ConfigUtil.QQW))
        {
            String url = baseUrl + "access_token=" + mOAuth2.getAccess_token()
                    + "&oauth_consumer_key=" + mOAuth2.getOauth_consumer_key()
                    + "&openid=" + mOAuth2.getOpenid();
            Thread thread = new Thread(new ClientThread(url, myHandler, 1002));
            thread.start();
        }
        else if (mConfigUtil.getCurWeibo().equals(ConfigUtil.SINAW))
        {
            String url = baseUrl + "access_token=" + mOAuth2.getAccess_token();
            Thread thread = new Thread(new ClientThread(url, myHandler, 1002));
            thread.start();
        }
    }
}
