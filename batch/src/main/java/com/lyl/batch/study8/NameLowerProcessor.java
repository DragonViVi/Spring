package com.lyl.batch.study8;

import com.lyl.batch.model.Template;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * Date:2018/12/17 0017 上午 9:17
 *
 * @author :liyunlong
 * @Description:
 * @UpdateDate: 2018/12/17 0017 上午 9:17
 */
@Component("nameLowerProcessor")
public class NameLowerProcessor implements ItemProcessor<Template, Template> {

    @Nullable
    @Override
    public Template process(Template template) throws Exception {
        String s = template.getName().toUpperCase();
        template.setName(s);
        return template;
    }


}
