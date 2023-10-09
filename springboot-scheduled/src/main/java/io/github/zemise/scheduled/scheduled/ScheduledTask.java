package io.github.zemise.scheduled.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableAsync // 开启异步事件的支持
@Component
public class ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void taskCron(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("现在时间Scheduled-1：" + dateFormat.format(new Date()));
    }

    @Async
    @Scheduled(fixedRate = 10000)
    public void taskFixed(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        logger.info("现在时间Scheduled-2：" + dateFormat.format(new Date()));
    }
}
