package com.diyishuai.tomato.utils;

import com.diyishuai.tomato.model.Msg;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.util.List;

/**
 * @author Bruce
 * @date 2016/11/11
 */
public class XMLUtils {


    /**
     * 从xml中解析对象
     * @param request
     * @return
     */
    public static Msg getMsgFromXml(HttpServletRequest request){
        Msg msg = new Msg();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(request.getInputStream());
            msg.setToUserName(document.selectSingleNode("//ToUserName").getText());
            msg.setFromUserName(document.selectSingleNode("//FromUserName").getText());
            msg.setCreateTime(document.selectSingleNode("//FromUserName").getText());
            msg.setMsgId(document.selectSingleNode("//MsgId").getText());
            String msgType = document.selectSingleNode("//MsgType").getText();
            if (Msg.MsgType.text.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.text);
                msg.setContent(document.selectSingleNode("//Content").getText());
            }else if (Msg.MsgType.image.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.image);
                msg.setMediaId(document.selectSingleNode("//MediaId").getText());
                msg.setPicUrl(document.selectSingleNode("//PicUrl").getText());
            }else if (Msg.MsgType.link.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.link);
            }else if (Msg.MsgType.location.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.location);
            }else if (Msg.MsgType.shortvideo.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.shortvideo);
            }else if (Msg.MsgType.video.toString().equals(msgType)){
                msg.setMsgType(Msg.MsgType.video);
            }else {

            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
