package com.atguigu.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("created 自动填充");
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());

        // 当age为null时，才去自动填充
        Object age = this.getFieldValByName("age", metaObject);
        if (null == age) {
            log.info("age字段 自动填充");
            this.strictInsertFill(metaObject, "age", Integer.class, 3);
        }

        // 有setAuthor方法才去填充
        boolean hasAuthor = metaObject.hasSetter("author");
        if (hasAuthor) {
            log.info("author字段 自动填充");
            this.strictInsertFill(metaObject, "author", String.class, "石头");
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("updated 自动填充");
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    }
}
