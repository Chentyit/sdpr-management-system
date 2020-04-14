package cn.chentyit.sdprms.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author Chentyit
 * @Date 2020/4/14 14:18
 * @Description:
 */
@Component
public class MetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("managerPermissions", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
