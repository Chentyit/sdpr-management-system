package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.service.ThesisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:31
 * @Description:
 */
@Service
public class ThesisServiceImpl implements ThesisService {

    @Resource
    private ThesisMapper thesisMapper;

    @Override
    public List<Thesis> getAllThesis() {
        return thesisMapper.selectList(null);
    }

    @Override
    public int deleteThesisById(List<Integer> ids) {
        int rows = thesisMapper.deleteBatchIds(ids);
        return rows;
    }
}
