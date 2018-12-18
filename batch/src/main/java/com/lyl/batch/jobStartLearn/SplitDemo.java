//package com.lyl.batch.study1;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.lang.Nullable;
//
///**
// * Date:2018/12/18 0018 上午 11:43
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 上午 11:43
// * <p>
// * 实现任务中多个Step或者多个flow并发执行
// * 1.创建若干个Step
// * 2.创建两个flow
// * 3.创建一个任务包含以上两个flow 并让这两个flow并发执行
// */
//@Configuration
//@EnableBatchProcessing
//public class SplitDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public SplitDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step stepSplitStep1() {
//        return stepBuilderFactory.get("stepSplitStep1").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepSplitStep1");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepSplitStep2() {
//        return stepBuilderFactory.get("stepSplitStep2").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepSplitStep2");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepSplitStep3() {
//        return stepBuilderFactory.get("stepSplitStep3").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepSplitStep3");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepSplitStep4() {
//        return stepBuilderFactory.get("stepSplitStep4").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepSplitStep4");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step stepSplitStep5() {
//        return stepBuilderFactory.get("stepSplitStep5").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("stepSplitStep5");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//
//    @Bean
//    public Flow splitFlow1() {
//        return new FlowBuilder<Flow>("splitFlow1")
//                .start(stepSplitStep1())
//                .next(stepSplitStep2())
//                .build();
//    }
//
//    @Bean
//    public Flow splitFlow2() {
//        return new FlowBuilder<Flow>("splitFlow2")
//                .start(stepSplitStep3())
//                .next(stepSplitStep4())
//                .build();
//    }
//
//    @Bean
//    public Job splitDemoJob1() {
//        return jobBuilderFactory.get("splitDemoJob1")
//                .start(splitFlow1())
//                .split(new SimpleAsyncTaskExecutor()).add(splitFlow2())
//                .next(stepSplitStep5())
//                .end()
//                .build();
//    }
//}
