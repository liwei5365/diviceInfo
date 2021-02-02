package com.nokia;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nokia.test.CamiServiceStatus;
import com.nokia.test.UsedStatus;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.net.InetAddress;

@Service
public class SendJms {

    private final Logger LOG = LoggerFactory.getLogger(SendJms.class);
    @Autowired
    private UsedStatus usedStatus;
    @Autowired
    private DeviceInfo deviceInfo;
    @Scheduled(cron = "${cron}")
    public  void send() {
        ConnectionFactory fc = new ActiveMQConnectionFactory("tcp://135.252.247.239:62626");
        Connection connection = null;
        try{
            connection = fc.createConnection("karaf", "karaf");
            connection.start();
            Session consumerSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination topic = consumerSession.createTopic("alarm");
            Session producerSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = consumerSession.createConsumer(topic);
            MessageProducer producer = producerSession.createProducer(topic);
            Connection finalConnection = connection;
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {

                        TextMessage tmsg = (TextMessage) message;
    //                    System.out.println("============:"+tmsg);
                        LOG.info("Message receiver{}.",message.getJMSMessageID().toString());
                        LOG.info("Message message{}",tmsg.getText());
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            });
            CamiServiceStatus serviceStatus = deviceInfo.execute();
            serviceStatus.setCamiHostIp(InetAddress.getLocalHost().getHostAddress());

            String usedJson = JSON.toJSONString(serviceStatus);
            LOG.info("Send message "+usedJson);
            producer.send(producerSession.createTextMessage(usedJson));
            Thread.sleep(1000);
        }catch (Exception e){
            LOG.warn("Error message this is error message");
        }finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            LOG.info("Connection message connection is close");
        }
    }
}
