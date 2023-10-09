package io.github.zemise.scheduled.quartz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Data
@Slf4j
public class SampleJob extends QuartzJobBean {
    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info(String.format("Hello %s", this.name));
    }
}
