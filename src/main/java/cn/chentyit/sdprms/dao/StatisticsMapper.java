package cn.chentyit.sdprms.dao;

import cn.chentyit.sdprms.model.pojo.NumberOfPublication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/15 16:50
 * @Description:
 */
@Mapper
public interface StatisticsMapper {

    /**
     * 获取每年各个主题数数量
     * @param year 10 年前的年份
     * @return
     */
    List<NumberOfPublication> getNumberOfPublication(@Param("year") Integer year);
}
