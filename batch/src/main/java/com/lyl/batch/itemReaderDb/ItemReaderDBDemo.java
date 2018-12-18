//package com.lyl.batch.itemReaderDb;
//
//import com.lyl.batch.model.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.lang.Nullable;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Date:2018/12/18 0018 下午 2:26
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/18 0018 下午 2:26
// */
//@Configuration
//public class ItemReaderDBDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final DataSource dataSource;
//
//    @Qualifier("userItemWriter")
//    private final UserItemWriter userItemWriter;
//
//    public ItemReaderDBDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource, UserItemWriter userItemWriter) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.dataSource = dataSource;
//        this.userItemWriter = userItemWriter;
//    }
//
//    @Bean
//    public Job itemReaderDbJob1() {
//        return jobBuilderFactory.get("itemReaderDbJob1")
//                .start(itemReaderDbStep1())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDbStep1() {
//        return stepBuilderFactory.get("itemReaderDbStep1")
//                .<User, User>chunk(2)
//                .reader(userJdbcPagingItemReader1())
//                .writer(userItemWriter)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<User> userJdbcPagingItemReader1() {
//        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(2);
//        //将读取的数据中转换为对象
//        reader.setRowMapper(new RowMapper<User>() {
//            @Nullable
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setUsername(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//                user.setPhone(resultSet.getString("phone"));
//                return user;
//            }
//        });
//        //指定Sql语句
//        MySqlPagingQueryProvider pagingQueryProvider = new MySqlPagingQueryProvider();
//        pagingQueryProvider.setSelectClause("id,username,password,phone");
//        pagingQueryProvider.setFromClause(" from t_user");
//        Map<String, Order> stringOrderMap = new HashMap<>();
//        stringOrderMap.put("id", Order.ASCENDING);
////        stringOrderMap.put("username", Order.DESCENDING);
//        pagingQueryProvider.setSortKeys(stringOrderMap);
//        reader.setQueryProvider(pagingQueryProvider);
//        return reader;
//    }
//}
