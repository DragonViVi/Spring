package com.lyl.batch.study1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Date:2018/12/13 0013 上午 10:13
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 上午 10:13
 */
//@SpringBootApplication
/**
 * 等价于Spring-context.xml
 */
@Configuration
/**
 * 自动装配SpringBatch相关配置
 */
@EnableBatchProcessing
public class ChildJob1 {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ChildJob1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean
    public Job childJobOne() {
        return jobBuilderFactory.get("childJobOne")
                //启动childJobStep1步骤
                .start(childJobStep1())
                //开始构建job
                .build();
    }

    @Bean
    public Step childJobStep1() {
        //创建一个名为childJobStep1的步骤
        return stepBuilderFactory.get("childJobStep1")
                //示例没有逻辑处理，只是一个简单的演示输出 ，使用TaskLet匿名内部类即可
                .tasklet(new Tasklet() {
                    @Nullable
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        //输出打印任务成功标识
                        System.out.println("Hello World");
                        //此处有两个值：FINISHED 步骤结束,CONTINUABLE步骤继续执行。
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
