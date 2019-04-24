package com.lovo.controller;

import com.lovo.activeMQ.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wt
 */
@Controller
public class WebSocketController {

    @Autowired
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
        for (int i = 0; i < 10; i++) {
            producer.sendMessage("testQueue001", "消息"+i);
            Thread.sleep(100);
        }
    }

    /**
     * 往队列二存入数据
     * @throws InterruptedException
     */
    @RequestMapping("sendMsg2")
    @ResponseBody
    public void sendMsg2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            producer.sendMessage("sb", "消息"+i);
            Thread.sleep(100);
        }
    }
}
