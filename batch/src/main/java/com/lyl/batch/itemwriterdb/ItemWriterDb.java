//package com.lyl.batch.itemwriterdb;
//
//import com.lyl.batch.model.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Date:2018/12/18 0018 下午 3:55
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 下午 3:55
// */
//@Configuration
//@EnableBatchProcessing
//public class ItemWriterDb {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Qualifier("flatFileItemReaderConfig")
//    private final ItemReader<User> flatFileItemReaderConfig;
//    @Qualifier("itemWriterConfig")
//    private final ItemWriter<? super User> itemWriterConfig;
//
//    public ItemWriterDb(JobBuilderFactory jobBuilderFactory,
//                        StepBuilderFactory stepBuilderFactory,
//                        ItemReader<User> flatFileItemReaderConfig,
//                        ItemWriter<User> itemWriterConfig) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.flatFileItemReaderConfig = flatFileItemReaderConfig;
//        this.itemWriterConfig = itemWriterConfig;
//    }
//
//
//    @Bean
//    public Job itemWriterDbDemoJob() {
//        return jobBuilderFactory.get("itemWriterDbDemoJob")
//                .start(itemWriterDbDemoStep())
//                .build();
//    }
//
//    private Step itemWriterDbDemoStep() {
//
//        return stepBuilderFactory.get("itemWriterDbDemoStep")
//                .<User, User>chunk(10)
//                .reader(flatFileItemReaderConfig)
//                .faultTolerant()
//                .writer(itemWriterConfig)
//                .build();
//    }
//}
//
//
//
