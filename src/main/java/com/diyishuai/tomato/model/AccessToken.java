package com.diyishuai.tomato.model;

/**
 * @author Bruce
 * @date 2016/11/12
 */
public class AccessToken {

    public static String ACCESS_TOKEN = "";

    private String access_token;

    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
