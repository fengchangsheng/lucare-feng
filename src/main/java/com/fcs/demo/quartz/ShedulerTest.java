package com.fcs.demo.quartz;

import org.quartz.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Lucare.Feng on 2016/10/19.
 */
public class ShedulerTest {

    public static void main(String[] args) throws SchedulerException, ParseException {

        QuartzManager.start();

        Scheduler sched = QuartzManager.getInstanceScheduler();

        JobDetail job = newJob(MyJob.class)
                .withIdentity("myJob", "group1")
//                .storeDurably()
                .build();
//        sched.addJob(job, false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println("开始时间: " + sdf.format(new Date()));

        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")
//                .forJob(jobKey("myJob", "group1"))
                .startAt(sdf.parse("2016-10-22 22:07:30"))
                .build();

        SimpleTrigger trigger2 = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1")
                .forJob(jobKey("myJob", "group1"))
//                .forJob("myJob")这是错误关键   没有指定组
                .startAt(sdf.parse("2016-10-22 22:07:38"))
                .build();

        sched.scheduleJob(job, trigger);
//        sched.scheduleJob(trigger);
        sched.scheduleJob(trigger2);
    }

    /**
     * 方法一
     * @throws SchedulerException
     * @throws ParseException
     */
    public void method1() throws SchedulerException, ParseException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        JobDetail job = newJob(MyJob.class).withIdentity("myJob", "group1").build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")
                .startAt(sdf.parse("2016-10-22 21:42:30"))
                .build();

        SimpleTrigger trigger2 = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1")
                .forJob(jobKey("myJob", "group1"))
                .startAt(sdf.parse("2016-10-22 21:42:38"))
                .build();

        sched.scheduleJob(job, trigger);
        sched.scheduleJob(trigger2);

    }

    public void method2() throws SchedulerException, ParseException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        // 加入一个任务到Quartz框架中, 等待后面再绑定Trigger
        JobDetail job = newJob(MyJob.class).withIdentity("myJob", "group1").storeDurably().build();
        sched.addJob(job, false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1")
                .startAt(sdf.parse("2016-10-22 21:42:30"))
                .build();

        SimpleTrigger trigger2 = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1")
                .startAt(sdf.parse("2016-10-22 21:42:38"))
                .build();

        sched.scheduleJob(trigger);
        sched.scheduleJob(trigger2);
    }

    // Add the new job to the scheduler, instructing it to "replace"
//  the existing job with the given name and group (if any)
//        JobDetail job1 = newJob(MyJobClass.class)
//                .withIdentity("job1", "group1")
//                .build();
//
//// store, and set overwrite flag to 'true'
//        scheduler.addJob(job1, true);
    // Trigger the job to run now, and then every 40 seconds
//        Trigger trigger = newTrigger()
//                .withIdentity("myTrigger", "group1")
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(40)
//                        .repeatForever())
//                .build();
    // 在当前时间15秒后运行
//        Date startTime = DateBuilder.nextGivenSecondDate(null,5);


}
