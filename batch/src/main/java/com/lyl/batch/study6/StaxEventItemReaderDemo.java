package com.lyl.batch.study6;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

/**
 * Date:2018/12/13 0013 下午 4:59
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 下午 4:59
 * 读取Xml文件
 */
@Configuration
@EnableBatchProcessing
public class StaxEventItemReaderDemo {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemWriter<Template> xmlFileWriter;

    public StaxEventItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<Template> xmlFileWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.xmlFileWriter = xmlFileWriter;
    }

    @Bean
    public Job staxEventJobDemo() {
        return jobBuilderFactory.get("staxEventJobDemo")
                .start(xmlItemReaderDemoStep())
                .build();
    }


    @Bean
    public Step xmlItemReaderDemoStep() {
        return stepBuilderFactory.get("xmlItemReaderDemoStep")
                .<Template, Template>chunk(3)
                .reader(staxEventItemReader())
                .writer(xmlFileWriter)
                .build();
    }


    @Bean
    @StepScope
    public StaxEventItemReader<Template> staxEventItemReader() {
        StaxEventItemReader<Template> reader = new StaxEventItemReader<>();
        //读取文件
        reader.setResource(new ClassPathResource("xml.xml"));
        //指定需要处理的根标签
        reader.setFragmentRootElementName("RECORD");
        // 把读取到的 xml 格式转为 Label
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        // 设置要读取的 xml 根节点 ---
        Map<String, Class> map = new HashMap<>(1);
        map.put("RECORD",Template.class);
        unmarshaller.setAliases(map);
        //marshller:写xml
        //unmarshaller:读xml
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }
}
