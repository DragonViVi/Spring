package com.lyl.batch.study8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyl.batch.model.Template;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2018/12/17 0017 上午 9:14
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/17 0017 上午 9:14
 * <p>
 * <p>
 * Processor 是在ItemReader 到ItemWriter 之间的一个数据转换操作。
 */
@Configuration
@EnableBatchProcessing
public class ProcessorDemo {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final ItemProcessor<Template, Template> nameLowerProcessor;

    private ItemProcessor<Template, Template> idFiltterProcessor;
//


    public ProcessorDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemProcessor<Template, Template> nameLowerProcessor, ItemProcessor<Template, Template> idFiltterProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.nameLowerProcessor = nameLowerProcessor;
        this.idFiltterProcessor = idFiltterProcessor;
    }

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .start(itemProcessorStep())
                .build();
    }

    @Bean
    public CompositeItemProcessor<Template, Template> processListener() {
        CompositeItemProcessor<Template, Template> processor = new CompositeItemProcessor<>();
        List<ItemProcessor<Template, Template>> list = new ArrayList<>(2);
        list.add(nameLowerProcessor);
//        // 数据过滤
        list.add(idFiltterProcessor);
        processor.setDelegates(list);
        return processor;

    }

    @Bean
    public Step itemProcessorStep() {
        return stepBuilderFactory.get("itemProcessorStep2")
                .<Template, Template>chunk(10)
                .reader(flatFileItemReader2())
                // 增加 Processor
                .processor(processListener())
                .writer(flatFileItemWriter2())
                .build();
    }


    @Bean
    @StepScope
    public FlatFileItemReader<Template> flatFileItemReader2() {
        FlatFileItemReader<Template> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data.txt"));
        //跳过第几行
        reader.setLinesToSkip(0);
        //声明数据解析
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        //声明数据分隔符,默认为英文逗号，可以设置为其他符号
        tokenizer.setDelimiter(",");
        tokenizer.setNames("name", "status");
        //解析后的数据映射为对象
        DefaultLineMapper<Template> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<Template>() {
            @Override
            public Template mapFieldSet(FieldSet fieldSet) throws BindException {
                Template template = new Template();
                template.setName(fieldSet.readString("name"));
                template.setStatus(fieldSet.readInt("status"));
                return template;
            }
        });
        //数据校验
        mapper.afterPropertiesSet();
        //绑定映射
        reader.setLineMapper(mapper);
        return reader;
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<Template> flatFileItemWriter2() {
        FlatFileItemWriter<Template> writer = new FlatFileItemWriter<>();
        String path = "D:\\city.txt";
        writer.setResource(new FileSystemResource(path));
        writer.setLineAggregator(new LineAggregator<Template>() {
            @Override
            public String aggregate(Template template) {
                try {
                    return new ObjectMapper().writeValueAsString(template);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });

        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

}
