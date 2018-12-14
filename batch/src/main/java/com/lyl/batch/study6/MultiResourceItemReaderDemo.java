//package com.lyl.batch.study6;
//
//import com.lyl.batch.model.Template;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.MultiResourceItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.validation.BindException;
//
//
///**
// * Date:2018/12/14 0014 上午 10:19
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 上午 10:19
// * <p>
// * 可以包含多个ResourceAwareItemReaderItemStream的子类，实现类所构建的文件读取ItemReader
// * 由此可一次读取多个文件
// */
//@Configuration
//@EnableBatchProcessing
//public class MultiResourceItemReaderDemo {
//    /**
//     * 常用实现类
//     * <p>
//     * 1. FlatFileItemReader：上一列中的文本文件读取
//     * 2. JsonItemReader:从json文件中读取
//     * 3. StaxEventItemReader：从xml 文件中读取数据
//     */
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final ItemWriter<Template> multiFileWriter;
//
//    public MultiResourceItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemWriter<Template> multiFileWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.multiFileWriter = multiFileWriter;
//    }
//
//    @Bean
//    public Job multiResourcesJob() {
//        return jobBuilderFactory.get("multiResourcesJob")
//                .start(multiResourceStep())
//                .build();
//    }
//
//
//    @Bean
//    public Step multiResourceStep() {
//        return stepBuilderFactory.get("multiResourceStep")
//                .<Template, Template>chunk(3)
//                .reader(multiFileReader())
//                .writer(multiFileWriter)
//                .build();
//    }
//
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Template> flatFileItemReader1() {
//        FlatFileItemReader<Template> reader = new FlatFileItemReader<>();
//        //解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("name", "status");
//        //解析后的数据映射为对象
//        DefaultLineMapper<Template> mapper = new DefaultLineMapper<>();
//        mapper.setLineTokenizer(tokenizer);
//        mapper.setFieldSetMapper(new FieldSetMapper<Template>() {
//            @Override
//            public Template mapFieldSet(FieldSet fieldSet) throws BindException {
//                Template template = new Template();
//                template.setName(fieldSet.readString("name"));
//                template.setStatus(fieldSet.readInt("status"));
//                return template;
//            }
//        });
//        // 数据校验
//        mapper.afterPropertiesSet();
//        // 绑定映射
//        reader.setLineMapper(mapper);
//        return reader;
//    }
//
//    // 引入 classpath下的所有以 file 开头的 txt 文件
//    @Value("classpath:file*.txt")
//    private Resource[] fileResources;
//
//    @Bean
//    @StepScope
//    public MultiResourceItemReader<Template> multiFileReader() {
//        MultiResourceItemReader<Template> reader = new MultiResourceItemReader<>();
//        // 文件读取
//        reader.setDelegate(flatFileItemReader1());
//        // 将所有文件放入 resources 中，即可实现多文件读取
//        reader.setResources(fileResources);
//        return reader;
//    }
//
//}
