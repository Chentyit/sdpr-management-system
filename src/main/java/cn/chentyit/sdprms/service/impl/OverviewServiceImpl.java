package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ScholarMapper;
import cn.chentyit.sdprms.dao.StatisticsMapper;
import cn.chentyit.sdprms.dao.ThemeMapper;
import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.model.entity.Theme;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.pojo.NumberOfPublication;
import cn.chentyit.sdprms.service.OverviewService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Chentyit
 * @Date 2020/4/15 09:47
 * @Description:
 */
@Service
public class OverviewServiceImpl implements OverviewService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Resource
    private ScholarMapper scholarMapper;

    @Resource
    private ThemeMapper themeMapper;

    @Resource
    private ThesisMapper thesisMapper;

    @Resource
    private LambdaQueryWrapper<Scholar> scholarLambdaQuery;

    @Resource
    private LambdaQueryWrapper<Theme> themeLambdaQuery;

    @Resource
    private LambdaQueryWrapper<Thesis> thesisLambdaQuery;

    @Override
    public Map<String, Integer> getSummaryData() {
        Map<String, Integer> result = new HashMap<>(16);

        // 获取学者数量
        scholarLambdaQuery.isNotNull(Scholar::getScholarName);
        result.put("scholar_num", scholarMapper.selectCount(scholarLambdaQuery));

        // 获取主题数量
        themeLambdaQuery.isNotNull(Theme::getThemeName);
        result.put("theme_num", themeMapper.selectCount(themeLambdaQuery));

        // 获取论文数量
        thesisLambdaQuery.isNotNull(Thesis::getThesisTitle)
                .isNotNull(Thesis::getThesisAuthor)
                .isNotNull(Thesis::getThesisClassic)
                .isNotNull(Thesis::getThesisBibtex);
        result.put("thesis_num", thesisMapper.selectCount(thesisLambdaQuery));

        return result;
    }

    @Override
    public List<NumberOfPublication> getNopData() {
        statisticsMapper.getNumberOfPublication().forEach(System.out::println);
        return null;
    }
}
