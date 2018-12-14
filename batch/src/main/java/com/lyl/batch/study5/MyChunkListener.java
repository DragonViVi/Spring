//package com.lyl.batch.study5;
//
//import org.springframework.batch.core.annotation.AfterChunk;
//import org.springframework.batch.core.annotation.BeforeChunk;
//import org.springframework.batch.core.scope.context.ChunkContext;
//
///**
// * Date:2018/12/13 0013 下午 3:20
// *
// * @author :liyunlong
// * @Description:
// * @UpdateDate: 2018/12/13 0013 下午 3:20
// * 注解的方式创建Listener
// */
//public class MyChunkListener {
//    @BeforeChunk
//    public void before(ChunkContext chunkContext) {
//
//        System.out.println("Step 执行之前,Step 名称：" + chunkContext.getStepContext().getStepName());
//    }
//    @AfterChunk
//    public void after(ChunkContext chunkContext){
//        System.out.println("Step 执行之后，Step 名称：" + chunkContext.getStepContext().getStepName());
//    }
//
//}
