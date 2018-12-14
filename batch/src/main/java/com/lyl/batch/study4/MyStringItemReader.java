//package com.lyl.batch.study4;
//
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.lang.Nullable;
//
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Date:2018/12/13 0013 下午 2:19
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 下午 2:19
// */
//public class MyStringItemReader implements ItemReader<String> {
//
//    public Iterator<String> iterator;
//
//    private int index;
//
//    public MyStringItemReader(List<String> data) {
//        this.iterator = data.iterator();
//    }
//
//    @Nullable
//    @Override
//    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        //一个数据一个数据的读取
//        if (iterator.hasNext()) {
//            return iterator.next();
//        } else {
//            return null;
//        }
//    }
//}
