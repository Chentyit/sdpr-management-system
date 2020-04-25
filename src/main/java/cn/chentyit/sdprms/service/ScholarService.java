package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.entity.Scholar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/20 17:51
 * @Description: 学者服务层
 */
public interface ScholarService extends IService<Scholar> {

    /**
     * 获取所有学者信息
     *
     * @return
     */
    List<Scholar> getAllScholar();

    /**
     * 通过学者名字查找 ID
     *
     * @param authorNames
     * @return
     */
    List<Integer> findScholarByName(List<String> authorNames);
}
