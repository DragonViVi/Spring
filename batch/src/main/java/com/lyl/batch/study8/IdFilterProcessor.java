package com.lyl.batch.study8;//package com.laiyy.batch.writer.itemprocessor;

import com.lyl.batch.model.Template;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
* @author laiyy
 * @date 2018/11/26 15:59
 * @description
 */
@Component("idFiltterProcessor")
public class IdFilterProcessor implements ItemProcessor<Template, Template> {
    @Override
   public Template process(Template city) throws Exception {
        if (city.getStatus() %2 == 0){
           return city;
       }
        // 如果返回 null，相当于把对象过滤掉
       return null;
    }
}
