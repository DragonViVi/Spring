//package com.lyl.batch.NestedJobLearn;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
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
// * Date:2018/12/18 0018 下午 1:11
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 下午 1:11
// */
//@Configuration
//public class NestedChildDemo2 {
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public NestedChildDemo2(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//    @Bean
//    public Step childJob2Step1() {
//        return stepBuilderFactory.get("childJob2Step1")
//                .tasklet(new Tasklet() {
//                    @Nullable
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJob2Step1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//    @Bean
//    public Step childJob2Step2() {
//        return stepBuilderFactory.get("childJob2Step2")
//                .tasklet(new Tasklet() {
//                    @Nullable
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJob2Step2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Bean
//    public Job childJobTwo() {
//        return jobBuilderFactory.get("childJobTwo")
//                .start(childJob2Step2())
//                .build();
//    }
//}
