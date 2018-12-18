//package com.lyl.batch.study4;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Date:2018/12/13 0013 下午 2:11
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 下午 2:11
// * ItemReader 可以理解为：数据获取。
// * 在 Step 中除了可以使用 Tasklet 创建简单的步骤，也可以使用 chunk + itemReader + itemWriter 创建一个复杂的步骤
// * <p>
// * <p>
// * <p>
// * 创建一个String 集合类型的ItemReader 并输出
// */
//@Configuration
//@EnableBatchProcessing
//public class ItemReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job itemReaderDemoJob() {
//        return jobBuilderFactory.get("itemReaderDemoJob")
//                .start(itemReaderDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDemoStep() {
//        return stepBuilderFactory.get("itemReaderDemoStep")
//                //指定数据输出为String,数据输出为String,从Reader中取够两条之后执行一次
//                .<String, String>chunk(2)
//                //指定数据源的intmReader
//                .reader(myStringItemReader())
//                //指定数据为打印数据
//                .writer(list -> {
//                    list.forEach(System.out::println);
//                }).build();
//    }
//
//
//    @Bean
//    public MyStringItemReader myStringItemReader() {
//        List<String> data = Arrays.asList("Cat", "Dog", "Pig", "Duck");
//        return new MyStringItemReader(data);
//    }
//}
