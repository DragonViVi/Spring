package com.lyl.batch.itemwriterdb;

import com.lyl.batch.model.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

/**
 * Date:2018/12/18 0018 下午 4:23
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/18 0018 下午 4:23
 */
@Configuration
@Component("flatFileItemReaderConfig")
public class FlatFileItemReaderConfig {
    @Bean
    public FlatFileItemReader<User> flatFileItemReaderToDb() {

        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("user.txt"));
        //跳过第几行
        reader.setLinesToSkip(0);
        //声明数据解析
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        //声明数据分隔符,默认为英文逗号，可以设置为其他符号
        tokenizer.setDelimiter(",");
        tokenizer.setNames("id", "password", "phone", "username");
        //解析后的数据映射为对象
        DefaultLineMapper<User> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(new FieldSetMapper<User>() {
            @Override
            public User mapFieldSet(FieldSet fieldSet) throws BindException {
                User user = new User();
                user.setId(fieldSet.readInt("id"));
                user.setPhone(fieldSet.readString("phone"));
                user.setPassword(fieldSet.readString("password"));
                user.setUsername(fieldSet.readString("username"));
                return user;
            }
        });
        //数据校验
        mapper.afterPropertiesSet();
        //绑定映射
        reader.setLineMapper(mapper);
        return reader;
    }
}
