package com.example.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wsHawk
 * @Title: SendController
 * @ProjectName just-for-test
 * @Description: TODO
 * @since 2020/11/24 23:02
 */
@RestController
public class SendController {
    @Autowired
    Producer producer;

    private static final String TOPIC = "test-topic";

    @RequestMapping("/send")
    public Object callback(String text) {
        Message message = new Message(TOPIC,"tag_a",("hello rocketmq ==>"+text).getBytes());
        try {
            SendResult send = producer.getProducer().send(message);
            System.out.println("send------>"+send);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
