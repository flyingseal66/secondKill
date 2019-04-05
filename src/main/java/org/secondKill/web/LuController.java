package org.secondKill.web;

import org.secondKill.java_learning.ThreadPool.lu.AppConfig;
import org.secondKill.java_learning.ThreadPool.lu.LoadSendTask;
import org.secondKill.java_learning.ThreadPool.lu.SendTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequestMapping("/lu")
@Controller
public class LuController {
    private final Logger logger = LoggerFactory.getLogger(LuController.class);

    @RequestMapping("/start")
    public void start() {
        logger.info("enter comtroller");
        LoadSendTask loadSendTask = new LoadSendTask();
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        threadPoolExecutor.scheduleWithFixedDelay(loadSendTask, AppConfig.INITIAL_DELAY, AppConfig.DELAY, TimeUnit.MILLISECONDS);

        //sendTask
        SendTask sendTask = new SendTask();
        threadPoolExecutor.scheduleWithFixedDelay(sendTask, 0, 100, TimeUnit.MILLISECONDS);
        //threadPoolExecutor.execute(sendTask);
    }
}
