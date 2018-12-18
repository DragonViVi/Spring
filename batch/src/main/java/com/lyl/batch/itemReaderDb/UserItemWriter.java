//package com.lyl.batch.itemReaderDb;
//
//
//import com.lyl.batch.model.User;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UserItemWriter implements ItemWriter<User> {
//    @Override
//    public void write(List<? extends User> list) throws Exception {
//        list.forEach(System.err::println);
//    }
//}