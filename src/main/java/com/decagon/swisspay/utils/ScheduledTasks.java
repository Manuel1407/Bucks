package com.decagon.swisspay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@EnableScheduling
public class ScheduledTasks {
    private static final Logger logger= LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(initialDelay = 10,fixedRate = 10,timeUnit = TimeUnit.SECONDS)
//    public void reportCurrentTime(){
//        logger.info("The time is now {}",dateFormat.format(new Date()));
//    }
}