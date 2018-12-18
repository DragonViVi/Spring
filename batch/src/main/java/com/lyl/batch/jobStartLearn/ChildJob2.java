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
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.Nullable;
//
///**
// * Date:2018/12/13 0013 上午 10:51
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 上午 10:51
// * SpringBatch一个任务包含N个步骤包括执行一些事物
// */
////@SpringBootApplication
//@Configuration
//@EnableBatchProcessing
//public class ChildJob2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ChildJob2(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job childJobTwo() {
//        return jobBuilderFactory.get("childJobTwo")
//                //先执行第二个步骤
//                .start(childJobStep2())
//                //再执行第三个步骤
//                .next(childJobStep3()).build();
//    }
//
//    @Bean
//    public Step childJobStep2() {
//        return stepBuilderFactory.get("childJobStep2").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("childJobStep2");
//                ////此处有两个值：FINISHED 步骤结束,CONTINUABLE步骤继续执行。
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step childJobStep3() {
//        return stepBuilderFactory.get("childJobStep3").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("childJobStep3");
//                //此处有两个值：FINISHED 步骤结束,CONTINUABLE步骤继续执行。
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//}
