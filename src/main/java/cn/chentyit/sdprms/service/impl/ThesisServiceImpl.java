package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.vo.ThesisVo;
import cn.chentyit.sdprms.service.ThesisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:31
 * @Description:
 */
@Service
public class ThesisServiceImpl extends ServiceImpl<ThesisMapper, Thesis> implements ThesisService {

    @Resource
    private ThesisMapper thesisMapper;

    @Override
    public List<Thesis> getAllThesis() {
        return thesisMapper.selectList(null);
    }

    @Override
    public int deleteThesisById(List<String> ids) {
        return thesisMapper.deleteBatchIds(ids);
    }
}
