//package com.lyl.batch.study1;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.Nullable;
//
///**
// * Date:2018/12/13 0013 上午 11:01
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 上午 11:01
// */
////@SpringBootApplication
//@Configuration
//@EnableBatchProcessing
//public class JobDemo {
//
//    // 执行成功后会返回的结果
//    private static final String COMPLETED = "COMPLETED";
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public JobDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//
//    @Bean
//    public Job jobDemoJob() {
//        return jobBuilderFactory.get("jobDemoJob")
////                .start(stepDemo1())
////                .next(stepDemo2())
////                .next(stepDemo3())
////                .next(stepDemo4())
////                .next(stepDemo5())
////                .build();
//                //1 跳转到3  on 表条件
//                .start(stepDemo1()).on(COMPLETED).to(stepDemo3())
//                //3跳转到4
//                .from(stepDemo3()).on(COMPLETED).to(stepDemo4())
//                //4跳转2结束
//                .from(stepDemo4()).on(COMPLETED).to(stepDemo2()).end().build();
//    }
//
//    @Bean
//    public Step stepDemo1() {
//        return stepBuilderFactory.get("stepDemo1").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepDemo1");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepDemo2() {
//        return stepBuilderFactory.get("stepDemo2").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepDemo2");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Step stepDemo3() {
//        return stepBuilderFactory.get("stepDemo3").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepDemo3");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepDemo4() {
//        return stepBuilderFactory.get("stepDemo4").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepDemo4");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepDemo5() {
//        return stepBuilderFactory.get("stepDemo5").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepDemo5");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//}
