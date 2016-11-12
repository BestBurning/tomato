package com.diyishuai.tomato.controller;

import com.diyishuai.tomato.WebApplication;
import com.diyishuai.tomato.model.Msg;
import com.diyishuai.tomato.utils.Sha1Utils;
import com.diyishuai.tomato.utils.XMLUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Bruce
 * @date 2016/11/6
 */
@Controller
@RequestMapping(value = "/wx")
public class WeChatController {

    @Autowired
    private Environment env;

    private static final Logger log = Logger.getLogger(WeChatController.class);


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
    public String hello(HttpServletRequest request){
        Msg requestMsg = XMLUtils.getMsgFromXml(request);
        Msg returnMsg = new Msg();
        returnMsg.setFromUserName(requestMsg.getToUserName());
        returnMsg.setToUserName(requestMsg.getFromUserName());
        returnMsg.setCreateTime(System.currentTimeMillis()+"");

        if (Msg.MsgType.text == requestMsg.getMsgType()){
            returnMsg.setMsgType(Msg.MsgType.text);
            returnMsg.setContent("接收到你的消息咯："+requestMsg.getContent());
        }else if (Msg.MsgType.image == requestMsg.getMsgType()){
            returnMsg.setMsgType(Msg.MsgType.image);
            returnMsg.setMediaId(requestMsg.getMediaId());
        }else if (Msg.MsgType.link == requestMsg.getMsgType()){
        }else if (Msg.MsgType.location == requestMsg.getMsgType()){
        }else if (Msg.MsgType.shortvideo == requestMsg.getMsgType()){
        }else if (Msg.MsgType.video == requestMsg.getMsgType()){
        }else {
            return "success";
        }
        log.info(returnMsg.toString());
        return returnMsg.toString();
    }

}
