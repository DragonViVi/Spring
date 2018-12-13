package com.lyl.batch.study2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Date:2018/12/13 0013 上午 11:18
 *
 * @author :liyunlong
 * @Description: 使用自定义的决策器, 进行步骤选择
 * @UpdateDate: 2018/12/13 0013 上午 11:18
 */
@Configuration
@EnableBatchProcessing
public class DeciderDemo {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public DeciderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * 引入自定义决策器
     *
     * @return
     */
    @Bean
    public JobExecutionDecider myCustomDecider() {
        return new MyCustomDecider();
    }

    @Bean
    public Job deciderDemoJob() {
        return jobBuilderFactory.get("deciderDemoJob")
                .start(deciderDemoStep1())
                //进入决策器
                .next(myCustomDecider())
                //如果决策器返回的even则进去第二步
                .from(myCustomDecider()).on("even").to(deciderDemoStep2())
                //如果决策器返回odd则进去第三步
                .from(myCustomDecider()).on("odd").to(deciderDemoStep3())
                // 由于先执行的是 step3，那么无论决策器返回值是什么都重新进入决策器，即：进入 next(myDecider())，这时会进入 step2 执行。
                // 如果不加这句，则只会执行 step3，不会执行 step2
                .from(deciderDemoStep3()).on("*").to(myCustomDecider())
                .end().build();
    }

    @Bean
    public Step deciderDemoStep3() {
        return stepBuilderFactory.get("deciderDemoStep3").tasklet(new Tasklet() {
            @Nullable
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("deciderDemoStep3");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step deciderDemoStep2() {
        return stepBuilderFactory.get("deciderDemoStep2").tasklet(new Tasklet() {
            @Nullable
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("deciderDemoStep2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step deciderDemoStep1() {
        return stepBuilderFactory.get("deciderDemoStep1").tasklet(new Tasklet() {
            @Nullable
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("deciderDemoStep1");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
