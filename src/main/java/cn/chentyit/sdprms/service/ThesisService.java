package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Thesis;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:30
 * @Description: 管理系统服务层
 */
public interface ThesisService {

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
    int deleteThesisById(List<Integer> ids);

    /**
     * 根据 Id 查询论文信息
     *
     * @param thesisId 论文 Id
     * @return
     */
    Thesis findThesisById(int thesisId);

    /**
     * 修改或插入数据
     *
     * @param thesisDTO
     * @return
     */
    int saveOrUpdateThesis(ThesisDTO thesisDTO);
}
