/*
 * Copyright (c) 2012 NeuLion, Inc. All Rights Reserved.
 */
package com.example.oauth2;

public class OAuth2
{
    private String access_token;
    private String expire_in;
    private String openid;//
    private String oauth_consumer_key;// appid /clientId
    private static OAuth2 mInstance;
    private ConfigUtil mConfig;

    private OAuth2()
    {
        mConfig = ConfigUtil.getInstance();
    }

    public static OAuth2 getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new OAuth2();
        }
        return mInstance;
    }

    public String getAuthURL()
    {
        // may Encode the content
        return mConfig.getAuthorize_url() + "response_type="
                + mConfig.getResponse_type() + "&" + "client_id="
                + mConfig.getClient_id() + "&" + "redirect_uri="
                + mConfig.getRedirect_uri();
    }

    public String getOpenURL()//QQ only
    {
        return mConfig.getOpenIdUrl() + "access_token=" + getAccess_token();
    }

    public void setAccessTokenAndExpireIn(String url)
    {
        if (!url.contains("access_token"))
        {
            return;
        }
        String params = url.split("#")[1];
        String[] paramsArray = params.split("&");
        setAccess_token(paramsArray[0].split("=")[1]);
        setExpire_in(paramsArray[1].split("=")[1]);
    }

    public void setOpenIdAndClientId(String data)
    {
        if (data==null)
        {
            return;
        }
            String[] paramsArray = data.split("&");
            setOauth_consumer_key(paramsArray[0].split("=")[1]);
            setOpenid(paramsArray[1].split("=")[1]);
    }

    public void requestAPI(String baseUrl)
    {
        if(mConfig.getCurWeibo().equals(ConfigUtil.QQW))
        {
            
        }
        else if(mConfig.getCurWeibo().equals(ConfigUtil.SINAW))
        {
            
        }
    }
    public String getAccess_token()
    {
        return access_token;
    }

    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }

    public String getExpire_in()
    {
        return expire_in;
    }

    public void setExpire_in(String expire_in)
    {
        this.expire_in = expire_in;
    }

    public String getOpenid()
    {
        return openid;
    }

    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOauth_consumer_key()
    {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key)
    {
        this.oauth_consumer_key = oauth_consumer_key;
    }
}
