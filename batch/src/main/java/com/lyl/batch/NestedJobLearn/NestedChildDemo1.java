//package com.lyl.batch.NestedJobLearn;
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
// * Date:2018/12/18 0018 下午 12:54
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 下午 12:54
// * <p>
// * Job的嵌套
// * <p>
// * 一个 job 可以嵌套到另一个 job 中  外部 job 称为父 job  子job不能单独执行，需要有父job来启动
// * 案例：创建两个job 在创建一个job作为父job
// */
//@Configuration
//@EnableBatchProcessing
//public class NestedChildDemo1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public NestedChildDemo1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step childJob1Step1() {
//        return stepBuilderFactory.get("childJob1Step1")
//                .tasklet(new Tasklet() {
//                    @Nullable
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJob1Step1");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Bean
//    public Step childJob1Step2() {
//        return stepBuilderFactory.get("childJob1Step2")
//                .tasklet(new Tasklet() {
//                    @Nullable
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("childJob1Step2");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Job childJobOne() {
//        return jobBuilderFactory.get("childJobOne")
//                .start(childJob1Step1())
//                .build();
//    }
//
//
//
//}
