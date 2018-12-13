package com.lyl.batch.study6;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Date:2018/12/13 0013 下午 5:22
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 下午 5:22
 */
public class XmlFileWriter implements ItemWriter<Template>{
    @Override
    public void write(List<? extends Template> list) throws Exception {
        list.forEach(System.out::println);
        System.err.println("===================");
    }
}
