package com.lovo.client;

import com.alibaba.fastjson.JSONObject;
import com.lovo.dto.NeedResubmitDto;
import com.lovo.util.DateUtil;
import com.lovo.util.MQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ActiveMqClient {
    @Autowired
    private MQUtil mqUtil;

   /* @RequestMapping("/send")
    public void send() {
        for (int i = 0; i < 10; i++){
            mqUtil.sendMQ("啦啦啦" + i);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

//   @RequestMapping("/send")
//    public void send(){
//       NeedResubmitDto re=new NeedResubmitDto();
//       re.setEventId("3");
//       re.setEventLevel("2");
//       re.setHurtPopulation("4");
//       re.setReportDesc("aaaaaaa");
//       re.setReportId("7");
//       re.setReportPeople("zzz");
//       re.setReportTel("1233232123");
//       re.setReportTime(DateUtil.getNowTime());
//       String s = JSONObject.toJSONString(re);
//       mqUtil.sendResubmit(s);
//   }
}
