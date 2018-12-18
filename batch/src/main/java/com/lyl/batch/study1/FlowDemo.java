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
//import org.springframework.lang.Nullable;
//
///**
// * Date:2018/12/18 0018 上午 11:13
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 上午 11:13
// * <p>
// * flow
// * <p>
// * 1.flow 是多个Step的集合
// * 2.可以被多个job复用
// * 3. 使用FlowBuilder来创建
// */
//@Configuration
//@EnableBatchProcessing
//public class FlowDemo {
//
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public FlowDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    /**
//     * 创建job对象
//     *
//     * @return
//     */
//    @Bean
//    public Job flowJob1() {
//        return jobBuilderFactory.get("flowJob1")
//                .start(flowDemoFlow())
//                .next(FlowStep3())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step FlowStep1() {
//        return stepBuilderFactory.get("FlowStep1").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("FlowStep1");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//    @Bean
//    public Step FlowStep2() {
//        return stepBuilderFactory.get("FlowStep2").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("flowStep2");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step FlowStep3() {
//        return stepBuilderFactory.get("FlowStep3").tasklet(new Tasklet() {
//            @Nullable
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("flowStep3");
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    /**
//     * 创建Flow对象 指明flow对象包含哪些Step
//     *
//     * @return
//     */
//    @Bean
//    public Flow flowDemoFlow() {
//        return new FlowBuilder<Flow>("flowDemoFlow")
//                .start(FlowStep1())
//                .next(FlowStep2())
//                .build();
//    }
//
//
//}
