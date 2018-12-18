//package com.lyl.batch.study6;
//
//import com.lyl.batch.model.Template;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.validation.BindException;
//
//import static java.util.stream.Collectors.toList;
//
///**
// * Date:2018/12/13 0013 下午 4:27
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 下午 4:27
// */
//
//@Configuration
//@EnableBatchProcessing
//public class flatFileReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public flatFileReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job flatFileReaderJob() {
//        return jobBuilderFactory.get("flatFileReaderJob")
//                .start(flatFileReader())
//                .build();
//    }
//
//    @Bean
//    public Step flatFileReader() {
//        return stepBuilderFactory.get("flatFileReader")
//                .<Template, Template>chunk(2)
//                .reader(flatFileItemReader())
//                .writer(list -> {
//                    list.stream().map(s -> s.getName()).collect(toList()).forEach(System.out::println);
//
//                }).build();
//    }
//
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Template> flatFileItemReader() {
//
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
//}
