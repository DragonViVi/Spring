//package com.lyl.batch.study7;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lyl.batch.model.Template;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.batch.item.file.transform.LineAggregator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.validation.BindException;
//
///**
// * Date:2018/12/14 0014 上午 11:32
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 上午 11:32
// * <p>
// * FlatFileItemWriter ： 将数据转为一个格式的字符串 将转好后的字符串写入文本文件中
// */
//@Configuration
//@EnableBatchProcessing
//public class FlatFileItemWriterDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public FlatFileItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job flatFileItemWriterJob1() {
//        return jobBuilderFactory.get("flatFileItemWriterJob1")
//                .start(flatFileItemWriterStep1())
//                .build();
//    }
//
//
//    @Bean
//    public Step flatFileItemWriterStep1() {
//        return stepBuilderFactory.get("flatFileItemWriterStep1")
//                .<Template, Template>chunk(3)
//                .faultTolerant()
//                .reader(flatFileItemReader2())
//                .writer(flatFileItemWriter2())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Template> flatFileItemReader2() {
//        FlatFileItemReader<Template> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("data.txt"));
//        //跳过第几行
//        reader.setLinesToSkip(0);
//        //声明数据解析
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        //声明数据分隔符,默认为英文逗号，可以设置为其他符号
//        tokenizer.setDelimiter(",");
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
//        //数据校验
//        mapper.afterPropertiesSet();
//        //绑定映射
//        reader.setLineMapper(mapper);
//        return reader;
//    }
//
//
//    @Bean
//    @StepScope
//    public FlatFileItemWriter<Template> flatFileItemWriter2() {
//        FlatFileItemWriter<Template> writer = new FlatFileItemWriter<>();
//        String path = "D:\\city.txt";
//        writer.setResource(new FileSystemResource(path));
//        writer.setLineAggregator(new LineAggregator<Template>() {
//            @Override
//            public String aggregate(Template template) {
//                try {
//                    return new ObjectMapper().writeValueAsString(template);
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//                return "";
//            }
//        });
//
//        try {
//            writer.afterPropertiesSet();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return writer;
//    }
//}
