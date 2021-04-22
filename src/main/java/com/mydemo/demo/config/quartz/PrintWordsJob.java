package com.mydemo.demo.config.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PrintWordsJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("job")+"111111");
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetail1")+"111111");
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("trigger")+"111111");
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("trigger1")+"111111");
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));

    }
}
