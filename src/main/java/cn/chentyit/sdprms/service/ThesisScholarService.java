package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.entity.ThesisScholar;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/20 17:54
 * @Description: 论文作者关联服务层
 */
public interface ThesisScholarService extends IService<ThesisScholar> {

    /**
     * 删除关联数据
     *
     * @param thesisId
     * @return
     */
    int deleteItem(String thesisId);
}
