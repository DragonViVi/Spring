package com.lyl.batch.study6;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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

    public StaxEventItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public StaxEventItemReader<Template> staxEventItemReader() {


        StaxEventItemReader<Template> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("staxEventXml.xml"));
        //
        reader.setFragmentRootElementName("RECORD");
        // 把读取到的 xml 格式转为 Label
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        // 设置要读取的 xml 根节点 ---
        Map<String, Class> map = new HashMap<>(1);
        map.put("RECORD",Template.class);
        unmarshaller.setAliases(map);
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }
}
