package com.lyl.batch.itemwriterdb;

import com.lyl.batch.model.User;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Date:2018/12/18 0018 下午 4:26
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/18 0018 下午 4:26
 */
@Configuration
@Component("itemWriterConfig")
public class ItemWriterConfig {

    private DataSource dataSource;

    public ItemWriterConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcBatchItemWriter<User> itemWriterDbWriter() {
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO  t_user (id,password,phone,username) VALUES " +
                "(:id,:password,:phone,:username)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }
}
