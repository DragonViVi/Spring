//package com.lyl.batch.study6;
//
//import com.lyl.batch.model.Template;
//import org.springframework.batch.item.ItemWriterLearn;
//
//import java.util.List;
//
///**
// * Date:2018/12/14 0014 上午 10:29
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/14 0014 上午 10:29
// */
//public class MultiFileWriter implements ItemWriterLearn<Template> {
//    @Override
//    public void write(List<? extends Template> list) throws Exception {
//        System.out.println("============MultiFileWriter============");
//        list.forEach(System.out::println);
//    }
//}
