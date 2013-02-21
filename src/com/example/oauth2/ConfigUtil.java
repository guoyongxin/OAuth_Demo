package com.example.oauth2;

/**
 * 获取配置文件
 * @author bywyu
 */
public class ConfigUtil
{

    private static ConfigUtil instance;

    private String curWeibo = "";
    private String client_id = "";
    private String client_secret = "";
    private String authorize_url = "";
    private String response_type = "";
    private String redirect_uri = "";
    private String openIdUrl = "";
    public static final String SINAW = "sina";
    public static final String QQW = "qq";

    // --------------------qq
    private final String authorizeUrl_qq = "https://graph.z.qq.com/moc2/authorize?";
    private final String openIdUrl_qq = "https://graph.z.qq.com/moc2/me?";
    private final String response_type_qq = "token";
    private final String client_id_qq = "100304545";
    private final String redirect_uri_qq = "";

    // --------------------sina
    private final String authorizeUrl_sina = "https://api.weibo.com/oauth2/authorize?";
    private final String accessTokenUrl_sina = "https://api.weibo.com/oauth2/access_token?";
    private final String response_type_sina = "token";
    private final String client_id_sina = "2052993658";
    private final String redirect_uri_sina = "https://api.weibo.com/oauth2/default.html";

    private ConfigUtil()
    {

    }

    public static ConfigUtil getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigUtil();
        }
        return instance;
    }

    public void initQQ()
    {
        setClient_id(client_id_qq);
        setAuthorize_url(authorizeUrl_qq);
        setResponse_type(response_type_qq);
        setRedirect_uri(redirect_uri_qq);
        setOpenIdUrl(openIdUrl_qq);
    }

    public void initSina()
    {
        setClient_id(client_id_sina);
        setAuthorize_url(authorizeUrl_sina);
        setResponse_type(response_type_sina);
        setRedirect_uri(redirect_uri_sina);
        setOpenIdUrl(accessTokenUrl_sina);
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }

    public String getAuthorize_url()
    {
        return authorize_url;
    }

    public void setAuthorize_url(String authorize_url)
    {
        this.authorize_url = authorize_url;
    }

    public String getResponse_type()
    {
        return response_type;
    }

    public void setResponse_type(String response_type)
    {
        this.response_type = response_type;
    }

    public String getRedirect_uri()
    {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri)
    {
        this.redirect_uri = redirect_uri;
    }

    public String getCurWeibo()
    {
        return curWeibo;
    }

    public void setCurWeibo(String curWeibo)
    {
        this.curWeibo = curWeibo;
    }

    public String getOpenIdUrl()
    {
        return openIdUrl;
    }

    public void setOpenIdUrl(String openIdUrl)
    {
        this.openIdUrl = openIdUrl;
    }

    public void setClient_secret(String client_secret)
    {
        this.client_secret = client_secret;
    }

}
