package com.fcs.demo.quartz;

import org.quartz.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Lucare.Feng on 2016/10/19.
 */
public class ShedulerTest {

    public static void main(String[] args) throws SchedulerException, ParseException {
//        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
//
//        Scheduler sched = schedFact.getScheduler();

//        sched.start();

        QuartzManager.start();

        Scheduler sched = QuartzManager.getInstanceScheduler();
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .build();
//        job.setDurable(true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 在当前时间15秒后运行
//        Date startTime = DateBuilder.nextGivenSecondDate(null,5);
        System.out.println("开始时间: "+ sdf.format(new Date()));
        // Trigger the job to run now, and then every 40 seconds
//        Trigger trigger = newTrigger()
//                .withIdentity("myTrigger", "group1")
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(40)
//                        .repeatForever())
//                .build();

        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")
                .startAt(sdf.parse("2016-10-19 22:53:30"))
                .build();

        SimpleTrigger trigger2 = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1")
                .startAt(sdf.parse("2016-10-19 22:53:35"))
                .build();


        // Tell quartz to schedule the job using our trigger

// 加入一个任务到Quartz框架中, 等待后面再绑定Trigger,
//        sched.addJob(job, false);
        sched.scheduleJob(job,trigger);
//        sched.scheduleJob(trigger2);
    }
}
