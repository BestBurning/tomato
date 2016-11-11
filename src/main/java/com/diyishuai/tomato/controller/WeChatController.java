package com.diyishuai.tomato.controller;

import com.diyishuai.tomato.model.Msg;
import com.diyishuai.tomato.utils.Sha1Utils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @date 2016/11/6
 */
@Controller
@RequestMapping(value = "/wx")
public class WeChatController {

    /**
     *  signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     *  timestamp	时间戳
     *  nonce	    随机数
     *  echostr	    随机字符串
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String weChatCheck(String signature,String timestamp,String nonce,String echostr){
        String token = "tomato";
        List<String> strings = Arrays.asList(token, timestamp, nonce);
        Collections.sort(strings);
        String temp = "";
        for (String str:strings) {
            temp+=str;
        }
        String result = Sha1Utils.getSha1(temp);
        if (result.equals(signature)){
            return echostr;
        }
        return "";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,produces = "application/xml")
    public Msg hello(HttpServletRequest request){

        System.out.println(request);
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key:parameterMap.keySet()) {
            System.out.println("key : "+key+"   value :"+parameterMap.get(key));
        }

        return new Msg();
    }

}
