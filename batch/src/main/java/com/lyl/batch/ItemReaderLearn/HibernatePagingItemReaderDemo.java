package com.lyl.batch.ItemReaderLearn;

//
///**
// * Date:2018/12/14 0014 上午 11:06
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 上午 11:06
// */
//@Configuration
//@EnableBatchProcessing
//public class HibernatePagingItemReaderDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public HibernatePagingItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//
//    @Bean
//    @StepScope
//    public ItemReader<News> hibernatePagingItemReader() {
//        HibernatePagingItemReader<News> reader = new HibernatePagingItemReader<>();
//        // 每页查询多少条
//        reader.setPageSize(500);
//        reader.setSessionFactory(sessionFactory);
//        HibernateNativeQueryProvider<News> provider = new HibernateNativeQueryProvider<>();
//        // 自动映射解析到哪个实体（该实体需要 @Entity 注解）
//        provider.setEntityClass(News.class);
//        String siteId = parameterMap.get("4500000001").toString();
//        int channelId = Integer.valueOf(parameterMap.get("2016").toString());
//        StringBuilder sql = new StringBuilder("SELECT * FROM zw_news where site_id =");
//        sql.append(siteId);
//        if (channelId != 0) {
//            sql.append(" and channel_id = ").append(channelId);
//        }
//        sql.append(" and status = 150 and del_status != 500");
//        sql.append(" order by news_weight desc, seq desc, pub_date desc, id desc ");
//        provider.setSqlQuery(sql.toString());
//        try {
//            // 参数校验
//            provider.afterPropertiesSet();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("获取 Hibernate 数据失败");
//        }
//        reader.setQueryProvider(provider);
//        // 可有可无
//        reader.setQueryName(" News");
//        reader.setUseStatelessSession(true);
//        return reader;
//    }


//}
