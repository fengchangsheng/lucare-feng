package com.fcs.demo.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static javafx.scene.input.KeyCode.J;

/**
 * Created by Lucare.Feng on 2016/10/19.
 */
public class MyJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // job 的名字
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();

        // 任务执行的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");
        String jobRunTime = dateFormat.format(Calendar.getInstance().getTime());

        // 输出任务执行情况
        System.out.println("任务 : " + jobName + " 在  " +jobRunTime + " 执行了 触发器为"+ jobExecutionContext.getTrigger().getKey().getName());

//        try {
//        QuartzManager.getInstanceScheduler().deleteJob(jobExecutionContext.getJobDetail().getKey());
//    } catch (SchedulerException e) {
//        e.printStackTrace();
//    }
    }
}
