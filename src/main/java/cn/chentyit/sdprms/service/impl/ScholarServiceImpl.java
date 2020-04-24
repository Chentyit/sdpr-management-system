package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ScholarMapper;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.service.ScholarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/20 17:52
 * @Description:
 */
@Service
public class ScholarServiceImpl extends ServiceImpl<ScholarMapper, Scholar> implements ScholarService {

    @Resource
    private ScholarMapper scholarMapper;

    @Override
    public List<Scholar> getAllScholar() {
        return scholarMapper.selectList(null);
    }
}
