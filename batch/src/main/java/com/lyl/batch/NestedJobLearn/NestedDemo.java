package com.lyl.batch.NestedJobLearn;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Date:2018/12/18 0018 下午 1:11
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/18 0018 下午 1:11
 */
@Configuration
public class NestedDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    /**
     * job 启动器
     */
    private final JobLauncher jobLauncher;

    private final Job childJobTwo;

    private final Job childJobOne;

    public NestedDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, JobLauncher jobLauncher, Job childJobTwo, Job childJobOne) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
        this.childJobTwo = childJobTwo;
        this.childJobOne = childJobOne;
    }

    @Bean
    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("parentJob")
                .start(childJob1(repository, transactionManager))
                .next(childJob2(repository, transactionManager))
                .build();
    }

    /**
     * 返回是Job类型的Step, 特殊的Step
     */
    private Step childJob2(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                //子job
                .job(childJobTwo)
                //启动器
                .launcher(jobLauncher)
                //持久化
                .repository(repository)
                //事务管理
                .transactionManager(transactionManager)
                .build();
    }

    private Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {

        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJobOne)
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }
}
