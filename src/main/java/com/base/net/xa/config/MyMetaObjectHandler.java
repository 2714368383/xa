package com.base.net.xa.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: zhaikaixuan
 * @Date: 2019-08-23 22:47
 * @Description: 默认自动填充字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("create_date", new Date(), metaObject);
        this.setFieldValByName("update_date", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("update_date", new Date(), metaObject);
    }
}
