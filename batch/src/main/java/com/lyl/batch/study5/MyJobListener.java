package com.lyl.batch.study5;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Date:2018/12/13 0013 下午 3:20
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 下午 3:20
 */
public class MyJobListener implements JobExecutionListener{
    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 在 job 执行之前执行
        System.out.println("Job 执行之前，Job 名称：" + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // 在 job 执行之后执行
        System.out.println("Job 执行之后，Job 名称：" + jobExecution.getJobInstance().getJobName());
    }
}
