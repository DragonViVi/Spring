//package com.lyl.batch.study2;
//
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.job.flow.FlowExecutionStatus;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.lang.Nullable;
//
///**
// * Date:2018/12/13 0013 上午 11:14
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 上午 11:14
// * 自定义决策器：
// * 根据不同的条件,返回不同的状态码
// * 根据状态码的不同，选择不用的步骤进行批量操作
// */
//public class MyCustomDecider implements JobExecutionDecider {
//
//
//    // 总处理条数
//    private int count;
//
//    @Override
//    public FlowExecutionStatus decide(JobExecution jobExecution, @Nullable StepExecution stepExecution) {
//        //每次进入决策器 则处理条数默认加1
//
//        count++;
//        if (count % 2 == 0) {
//            return new FlowExecutionStatus("even");
//        } else {
//            return new FlowExecutionStatus("odd");
//        }
//    }
//}
