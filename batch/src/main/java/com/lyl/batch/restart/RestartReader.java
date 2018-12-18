//package com.lyl.batch.restart;
//
//import com.lyl.batch.model.City;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.ItemStreamException;
//import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BindException;
//
///**
// * Date:2018/12/17 0017 下午 3:22
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/17 0017 下午 3:22
// */
//@Component
//public class RestartReader implements ItemStreamReader<City> {
//    /**
//     * 文件读取
//     */
//    private FlatFileItemReader<City> reader = new FlatFileItemReader<>();
//    /**
//     * 当前读到第几行
//     */
//    private Long curLine = 0L;
//    /**
//     *
//     */
//    private Boolean restart = false;
//    /**
//     * 执行的上下文 来向数据库中执行数据
//     */
//    private ExecutionContext executionContext;
//
//    /**
//     * 读取/写入数据的规则
//     *
//     * @return
//     * @throws Exception
//     * @throws UnexpectedInputException
//     * @throws ParseException
//     * @throws NonTransientResourceException
//     */
//
//    public RestartReader() {
//        reader.setResource(new ClassPathResource("error.txt"));
//
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("id", "name", "countryCode", "district", "population");
//        //解析后的数据映射为对象
//        DefaultLineMapper<City> mapper = new DefaultLineMapper<>();
//        mapper.setLineTokenizer(tokenizer);
//
//        mapper.setFieldSetMapper(new FieldSetMapper<City>() {
//            @Override
//            public City mapFieldSet(FieldSet fieldSet) throws BindException {
//                City city = new City();
//                city.setCountryCode(fieldSet.readString("countryCode"));
//                city.setDistrict(fieldSet.readString("district"));
//                city.setId(fieldSet.readInt("id"));
//                city.setName(fieldSet.readString("name"));
//                city.setPopulation(fieldSet.readLong("population"));
//                return city;
//            }
//        });
//        mapper.afterPropertiesSet();
//        reader.setLineMapper(mapper);
//
//    }
//
//    @Nullable
//    @Override
//    public City read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//
//        City city = null;
//        this.curLine++;
//        if (restart) {
//            //如果是重启(出现错误之后),则从chunk记录开始读取
//            reader.setLinesToSkip(this.curLine.intValue() - 1);
//            //将重启值置为false,否则将会重复读取
//            restart = false;
//            System.out.println("Start reading from line: " + this.curLine);
//        }
//        reader.open(this.executionContext);
//        city = reader.read();
//        if (city != null && this.curLine == 3) {
//            throw new RuntimeException("Something Wrong!");
//        }
//        return city;
//    }
//
//    /**
//     * 在开始读取之前调用
//     */
//    @Override
//    public void open(ExecutionContext executionContext) throws ItemStreamException {
//        // 获取当前执行上下文
//        this.executionContext = executionContext;
//        if (executionContext.containsKey("curLine")) {
//            // 如果执行上下文存在 cutLine，则证明执行为 重启后执行
//            this.curLine = executionContext.getLong("curLine");
//            // 将重启值置为 true
//            this.restart = true;
//        } else {
//            // 第一次执行，向执行上下文中打入 curLine 记录（会记录进数据库）
//            this.curLine = 0L;
//            executionContext.put("curLine", 0L);
//            System.out.println("Start reading from line: " + (this.curLine + 1));
//        }
//    }
//
//
//    /**
//     * update：在chunk后执行，用于修改数据库中对ExecutionContext的记录
//     *
//     * @param executionContext
//     * @throws ItemStreamException
//     */
//    @Override
//    public void update(ExecutionContext executionContext) throws ItemStreamException {
//        executionContext.put("curLine", this.curLine);
//        System.out.println("Reading line: " + (this.curLine + 1));
//    }
//
//    /**
//     * 读取/写入 结束后执行
//     *
//     * @throws ItemStreamException
//     */
//    @Override
//    public void close() throws ItemStreamException {
//
//    }
//}
