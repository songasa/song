package com.zut.service_parking.utils.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author song
 */
@Component
public class MyMeteObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("parkingCreatetime", new Date(), metaObject);
        this.setFieldValByName("logCreatetime", new Date(), metaObject);
        this.setFieldValByName("userCreatetime", new Date(), metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("logUpdatetime", new Date(), metaObject);
    }
}
