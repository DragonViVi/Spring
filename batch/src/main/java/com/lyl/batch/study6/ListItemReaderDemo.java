//package com.lyl.batch.study6;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * Date:2018/12/13 0013 下午 4:12
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 下午 4:12
// * <p>
// * ListItemReader : 声明一个集合作为数据的输出，通常数据量较小，内存可以处理的批处理操作
// * 数据量过大的话，放入ListItemReader中可能造成内存溢出
// */
//@Configuration
//@EnableBatchProcessing
//public class ListItemReaderDemo {
//
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ListItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job listItemReaderJob1() {
//        return jobBuilderFactory.get("listItemReaderJob1")
//                .start(listItemReaderStep2())
//                .build();
//    }
//
//    @Bean
//    public Step listItemReaderStep2() {
//        return stepBuilderFactory.get("listItemReaderStep1")
//                .<String, String>chunk(3)
//                .faultTolerant()
//                .reader(itemReader2())
//                .writer(list -> {
//                    list.forEach(System.out::println);
//                }).build();
//    }
//
//    @Bean
//    public ItemReader<String> itemReader2() {
//        return new ListItemReader<>(Arrays.asList("12", "121", "1212", "121", "1221"));
//    }
//}
