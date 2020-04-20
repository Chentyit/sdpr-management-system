package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Thesis;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:30
 * @Description: 管理系统服务层
 */
public interface ThesisService extends IService<Thesis> {

    /**
     * 获取所有论文数据信息
     *
     * @return
     */
    List<Thesis> getAllThesis();

    /**
     * 根据论文信息 Id 删除数据
     *
     * @param ids 需要删除的论文信息 Id
     * @return
     */
    int deleteThesisById(List<String> ids);
}
