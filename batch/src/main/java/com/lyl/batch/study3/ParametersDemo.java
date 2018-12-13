package com.lyl.batch.study3;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Date:2018/12/13 0013 上午 11:44
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 上午 11:44
 * <p>
 * 在 job  运行期间获取JobParameters
 * <p>
 * Map<String, JobParameter> 声明为公开的静态变量，在Listener中使用
 * <p>
 * 使用Jpb的类实现 StepExecutionListener接口,直接在本类中使用私有变量调用
 */
@Configuration
@EnableBatchProcessing
public class ParametersDemo implements StepExecutionListener {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    public ParametersDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    /**
     * 存储 Job 的参数
     */
    private Map<String, JobParameter> parameterMap;

    @Bean
    public Job parameterJob() {
        return jobBuilderFactory.get("parameterJob")
                .start(parameterStep())
                .build();
    }


    @Bean
    public Step parameterStep() {
        return stepBuilderFactory.get("parameterStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Nullable
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        for (String s : parameterMap.keySet()) {
                            System.out.println("key : " + s + " value : " + parameterMap.get(s));
                        }
                        System.out.println("【parameterStep】接收到的参数： " + parameterMap.get("info"));
                        return null;
                    }
                }).build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("在 Step 执行之前传入参数");
        // stepExecution.getJobParameters().getParameters() 是在项目执行时传入的参数，即：项目运行参数
        parameterMap = stepExecution.getJobParameters().getParameters();
        parameterMap.put("info", new JobParameter("1111"));
    }

    @Nullable
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("在 Step 执行之后处理结果");
        return null;
    }
}
