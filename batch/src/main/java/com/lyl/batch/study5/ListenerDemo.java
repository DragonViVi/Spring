package com.lyl.batch.study5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Date:2018/12/13 0013 下午 3:22
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 下午 3:22
 *
 * Step 执行之前,Step 名称：listenerStep1
===========1
=============2
Step 执行之后，Step 名称：listenerStep1
Step 执行之前,Step 名称：listenerStep1
==========3
=============4
Step 执行之后，Step 名称：listenerStep1
Step 执行之前,Step 名称：listenerStep1
Step 执行之后，Step 名称：listenerStep1
 */
@Configuration
@EnableBatchProcessing
public class ListenerDemo {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public ListenerDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job listenerJob() {
        return jobBuilderFactory.get("listenerJob")
                .start(listenerStep1())
                .build();
    }

    @Bean
    public Step listenerStep1() {
        return stepBuilderFactory.get("listenerStep1")
                //以Chunk方式设置为每读取2个数据做一次相应的处理
                //read,process,write;<String,String> 读取String,输出String
                .<String, String>chunk(2)
                //容错
                .faultTolerant()
                //以创建对象方式引入chunkListener 也可以主语
                //设置chunk监听
                .listener(new MyChunkListener())
                //读取数据
                .reader(itemReader())
                //输出数据
                .writer(itemWriter())
                .build();
    }


    @Bean
    public ItemWriter<String> itemWriter() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.forEach(System.out::println);
            }
        };
    }

    @Bean
    public ItemReader<String> itemReader() {
        return new ListItemReader<>(Arrays.asList("===========1", "=============2", "==========3", "=============4"));
    }
}
