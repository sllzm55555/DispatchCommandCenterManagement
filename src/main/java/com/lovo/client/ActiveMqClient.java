package com.lovo.client;

import com.lovo.util.MQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ActiveMqClient {
    @Autowired
    private MQUtil mqUtil;

    @RequestMapping("/send")
    public void send() {
        for (int i = 0; i < 10; i++){
            mqUtil.sendMQ("啦啦啦" + i);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
