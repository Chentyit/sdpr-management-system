package cn.chentyit.sdprms.dao;

import cn.chentyit.sdprms.model.entity.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author Chentyit
 * @Date 2020/4/14 11:36
 * @Description:
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
}
