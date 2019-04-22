package com.lovo.testActiveMQ;

/**
 * @author 王涛
 */
//@Component(value = "consumer")
public class Consumer {
    /**
     * 使用JmsListener配置消费者监听的队列，其中message是接收到的消息,此方法会一直运行相当于一个死循环
     */
//    @JmsListener(destination="testQueue")
    public void receiveQueue(String message){
        System.out.println(message);
    }
}
