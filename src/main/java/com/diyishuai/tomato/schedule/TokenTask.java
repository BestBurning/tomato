package com.diyishuai.tomato.schedule;

import com.alibaba.fastjson.JSON;
import com.diyishuai.tomato.model.AccessToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Bruce
 * @date 2016/11/12
 */
@Component
public class TokenTask {

    @Scheduled(fixedRate = 7200000)
    public void refreshToken() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" +
                        "appid=wx474f1297597cdc1d&" +
                        "secret=7fdbe403044b8734d92f974220a115d0")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            AccessToken accessToken = JSON.parseObject(response.body().string(), AccessToken.class);
            AccessToken.accessToken = accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
