package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ScholarMapper;
import cn.chentyit.sdprms.dao.ThesisScholarMapper;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.model.entity.ThesisScholar;
import cn.chentyit.sdprms.service.ThesisScholarService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/20 17:56
 * @Description:
 */
@Service
public class ThesisScholarServiceImpl extends ServiceImpl<ThesisScholarMapper, ThesisScholar> implements ThesisScholarService {

    @Resource
    private ThesisScholarMapper thesisScholarMapper;

    @Override
    public int deleteItem(String thesisId) {
        LambdaQueryWrapper<ThesisScholar> thesisScholarLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thesisScholarLambdaQueryWrapper.eq(ThesisScholar::getThesisId, thesisId);
        return thesisScholarMapper.delete(thesisScholarLambdaQueryWrapper);
    }
}
