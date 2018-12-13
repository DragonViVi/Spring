package com.lyl.batch.study3;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Date:2018/12/13 0013 上午 11:37
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/13 0013 上午 11:37
 * 在Step中获取JobParmeters,通常使用StepExecutionListener监听器
 * <p>
 * 在某个Step中传入监听器,这个监听器就能获取到这个Step的所有上下文信息
 * 在监听器的实现放房中，进行上线文信息的获取，JobParameters的获取，Step执行前后的上下文处理等操作
 */
public class MyStepListener implements StepExecutionListener {

    /**
     * 存储 job 的参数
     */
    private Map<String, JobParameter> parameterMap;

    /**
     * 在Step执行前获取Step的上下文信息
     *
     * @param stepExecution
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        //在Step执行之前执行
        System.out.println("执行之前：step 名称：" + stepExecution.getStepName());
        /**
         * 获取到 JobParameters的信息
         */
        parameterMap = stepExecution.getJobParameters().getParameters();
    }

    /**
     * 在Step执行后获取Step的上下文信息
     *
     * @param stepExecution
     * @return
     */
    @Nullable
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //在Step执行之前执行
        System.out.println("执行之后：step 名称：" + stepExecution.getStepName());
        /**
         * 获取到 JobParameters的信息
         */
        parameterMap = stepExecution.getJobParameters().getParameters();
        return null;
    }
}
