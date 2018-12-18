//package com.lyl.batch.restart;
//
//import com.lyl.batch.model.City;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Date:2018/12/17 0017 下午 5:23
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/17 0017 下午 5:23
// * <p>
// * ItemReader 读取数据的时候的异常处理
// */
//@Configuration
//@EnableBatchProcessing
//public class RestartDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<City> restartWriter;
//
//    private final ItemReader<City> restartReader;
//
//    public RestartDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<City> restartWriter, ItemReader<City> restartReader) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.restartWriter = restartWriter;
//        this.restartReader = restartReader;
//    }
//
//
//    @Bean
//    public Job restartDemoJob() {
//        return jobBuilderFactory.get("restartDemoJob")
//                .start(restartDemoStep())
//                .build();
//    }
//
//
//    @Bean
//    public Step restartDemoStep() {
//        return stepBuilderFactory.get("restartDemoStep")
//                .<City, City>chunk(2)
//                .reader(restartReader)
//                .writer(restartWriter)
//                .build();
//    }
//
//
//}
