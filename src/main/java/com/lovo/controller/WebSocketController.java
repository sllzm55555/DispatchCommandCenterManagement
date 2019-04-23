package com.lovo.controller;

import com.lovo.testActiveMQ.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class WebSocketController {

    @Resource(name = "producer")
    Producer producer;

    @RequestMapping("websocket")
    public String websocket() {
        return "webSocket/websocket";
    }

    @RequestMapping("websocket02")
    public String websocket02() {
        return "webSocket/websocket02";
    }

    /**
     * 往队列一存入数据
     * @throws InterruptedException
     */
    @RequestMapping("sendMsg")
    @ResponseBody
    public void sendMsg() throws InterruptedException {
        producer.sendMessage();
    }

    /**
     * 往队列二存入数据
     * @throws InterruptedException
     */
    @RequestMapping("sendMsg2")
    @ResponseBody
    public void sendMsg2() throws InterruptedException {
        producer.sendMessage2();
    }
}
