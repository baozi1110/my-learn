package com.qp.mywebsocket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 消息推送 与22socket.html对应
 **/
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口,匹配多个路径，当cid为空时群发
    @ResponseBody
    @RequestMapping(value = {"/socket/push/{cid}","/socket/push"})
    public String pushToWeb(@PathVariable(value = "cid",required =false) String cid, String message) {
        try {
            WebSocketServer01.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return cid + "#" + e.getMessage();
        }
        return cid;
    }
}
