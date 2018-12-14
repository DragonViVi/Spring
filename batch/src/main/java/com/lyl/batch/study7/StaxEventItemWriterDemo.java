//package com.lyl.batch.study7;
//
//import com.lyl.batch.model.Template;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.batch.item.xml.StaxEventItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Date:2018/12/14 0014 下午 3:43
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 下午 3:43
// */
//@Configuration
//@EnableBatchProcessing
//public class StaxEventItemWriterDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public StaxEventItemWriterDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
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
//                .reader(staxEventItemReader())
//                .writer(staxEventItemWriter())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public StaxEventItemReader<Template> staxEventItemReader() {
//        StaxEventItemReader<Template> reader = new StaxEventItemReader<>();
//        //读取文件
//        reader.setResource(new ClassPathResource("xml.xml"));
//        //指定需要处理的根标签
//        reader.setFragmentRootElementName("RECORD");
//        // 把读取到的 xml 格式转为 Label
//        XStreamMarshaller unmarshaller = new XStreamMarshaller();
//        // 设置要读取的 xml 根节点 ---
//        Map<String, Class> map = new HashMap<>(1);
//        map.put("RECORD", Template.class);
//        unmarshaller.setAliases(map);
//        //marshller:写xml
//        //unmarshaller:读xml
//        reader.setUnmarshaller(unmarshaller);
//        return reader;
//    }
//
//
//    @Bean
//    @StepScope
//    public StaxEventItemWriter<Template> staxEventItemWriter() {
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
//        String path = "D:\\city.txt";
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
