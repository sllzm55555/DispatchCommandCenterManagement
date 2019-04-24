import com.alibaba.fastjson.JSONObject;
import com.lovo.DispatchCommandCenterManagementApplication;
import com.lovo.activeMQ.Producer;
import com.lovo.entity.PageBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DispatchCommandCenterManagementApplication.class)
public class TestActiveMQ {

    @Autowired
    Producer producer;

    @Test
    public void addMsg() throws InterruptedException {
        PageBean<String> stringPageBean = new PageBean<>();
        stringPageBean.setCurrPate(1);
        stringPageBean.setTotalPate(5);
        String string = JSONObject.toJSONString(stringPageBean);
        for (int i = 0; i < 10; i++) {
            producer.sendMessage("sb", string + i);
        }
    }


}
