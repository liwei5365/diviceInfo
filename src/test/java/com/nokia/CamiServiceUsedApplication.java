package com.nokia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
@EnableScheduling
public class CamiServiceUsedApplication {

    @Test
    public void test1() {

//        UsedStatus usedStatus = new UsedStatus();
//        try {
//            usedStatus.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                UsedStatus usedStatus = new UsedStatus();
                try {
                    usedStatus.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0,1000);*/

    }
}
