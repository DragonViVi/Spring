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
//import org.springframework.batch.item.ItemWriterLearn;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.batch.item.file.transform.LineAggregator;
//import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
//import org.springframework.batch.item.xml.StaxEventItemWriter;
//import org.springframework.classify.Classifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//import org.springframework.validation.BindException;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Date:2018/12/14 0014 下午 3:52
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 下午 3:52
// * <p>
// * CompositeItemWriter 可以将多个文本文件的写入方式结合在一起，实现写入多个文件的操作
// */
//@Configuration
//@EnableBatchProcessing
//public class CompositeItemWriterDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public CompositeItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job compositeItemJob() {
//        return jobBuilderFactory.get("compositeItemJob")
//                .start(compositeItemStep())
//                .build();
//    }
//
//
//    @Bean
//    public Step compositeItemStep() {
//        return stepBuilderFactory.get("compositeItemStep")
//                .<Template, Template>chunk(2)
//                .reader(flatFileItemReader3())
//                .faultTolerant()
//                .writer(multiFileWriter1())
//                .build();
//    }
//
//    @Bean
//    public ClassifierCompositeItemWriter<Template> multiFileWriter1() {
//        ClassifierCompositeItemWriter<Template> writer = new ClassifierCompositeItemWriter<>();
//
//        writer.setClassifier(new Classifier<Template, ItemWriterLearn<? super Template>>() {
//            @Override
//            public ItemWriterLearn<? super Template> classify(Template city) {
//                // 按照 City 的 id 进行分类
//                if (city.getStatus() % 2 == 0){
//                    return txtItemWriter2();
//                } else {
//                    return xmlItemWriter();
//                }
//            }
//        });
//        return writer;
//    }
////    @Bean
////    @StepScope
////    public CompositeItemWriter<Template> compositeItemWriter() {
////        CompositeItemWriter<Template> writer = new CompositeItemWriter<>();
////        writer.setDelegates(Arrays.asList(txtItemWriter2(), xmlItemWriter()));
////        try {
////            writer.afterPropertiesSet();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return writer;
////    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Template> flatFileItemReader3() {
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
//    @Bean
//    @StepScope
//    public FlatFileItemWriter<Template> txtItemWriter2() {
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
//
//    @Bean
//    @StepScope
//    public StaxEventItemWriter<Template> xmlItemWriter() {
//        StaxEventItemWriter<Template> writer = new StaxEventItemWriter<>();
//        //xml 处理器
//        XStreamMarshaller marshaller = new XStreamMarshaller();
//// 指定根节点
//        Map<String, Class> aliases = new HashMap<>(1);
//        aliases.put("temple", Template.class);
//        marshaller.setAliases(aliases);
//
//        // setMarshaller：写为 xml，setUnMarshaller：读 xml
//        writer.setMarshaller(marshaller);
//
//        // 文件路径
//        String path = "D:\\city.xml";
//        writer.setResource(new FileSystemResource(path));
//
//        try {
//            // 参数校验
//            writer.afterPropertiesSet();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return writer;
//    }
//}
