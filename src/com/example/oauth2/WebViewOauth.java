/*
 * Copyright (c) 2012 NeuLion, Inc. All Rights Reserved.
 */
package com.example.oauth2;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class WebViewOauth extends WebViewClient
{
    Handler myHandler;
    public WebViewOauth(Handler handler)
    {
        myHandler = handler;
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler,
            SslError error)
    {
        handler.proceed();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    {
        // maybe show loading here
        OAuth2 oAuth2 = OAuth2.getInstance();
        if (url.contains("access_token") && url.contains("expires_in"))//
        {
            System.out.println("accessToken got:" + url);
            oAuth2.setAccessTokenAndExpireIn(url);
            if (ConfigUtil.getInstance().getCurWeibo().equals(ConfigUtil.QQW))//
            {
                view.loadUrl(oAuth2.getOpenURL());
            }
        }
        if (url.contains("access_token") && !url.contains("expires_in"))//
        {
            System.out.println("open_id got:" + url);
            Thread thread = new Thread(new ClientThread(url, myHandler,
                    1001));
            thread.start();
        }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url)
    {
        // loading finish
        super.onPageFinished(view, url);
    }
}


class ClientThread implements Runnable
{
    private String mUrl;
    private int mWhat;
    private Handler mHandler;

    public ClientThread(String url, Handler handler, int what)
    {
        mUrl = url;
        mWhat = what;
        mHandler = handler;
    }

    public void run()
    {
        Message message = new Message();
        message.what = mWhat;
        try
        {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(mUrl);
            String content = EntityUtils.toString(httpClient.execute(get)
                    .getEntity());
            message.obj = content;
            mHandler.sendMessage(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}