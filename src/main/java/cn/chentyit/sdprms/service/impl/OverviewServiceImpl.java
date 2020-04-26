package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ScholarMapper;
import cn.chentyit.sdprms.dao.StatisticsMapper;
import cn.chentyit.sdprms.dao.ThemeMapper;
import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.model.entity.Theme;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.pojo.NumberOfPublication;
import cn.chentyit.sdprms.model.vo.DofVo;
import cn.chentyit.sdprms.service.OverviewService;
import cn.chentyit.sdprms.util.ResultPackUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    /**
     * 获取 学者 || 主题 || 论文 总数
     *
     * @return
     */
    @Override
    public Map<String, Integer> getSummaryData() {
        LambdaQueryWrapper<Scholar> scholarLambdaQuery = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Theme> themeLambdaQuery = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Thesis> thesisLambdaQuery = new LambdaQueryWrapper<>();

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

    /**
     * 获取走势图数据
     *
     * @return
     */
    @Override
    public List<Object> getNopData() {
        // 获取当前系统时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 获取 10 年内每每年的各个主题的数据量
        List<NumberOfPublication> nopData = statisticsMapper.getNumberOfPublication(localDateTime.getYear() - 9);
        // 查询出所有的主题数据
        List<Theme> themes = themeMapper.selectList(null);

        Map<String, Map<String, Integer>> result = new HashMap<>(16);

        // 构造数据存储格式
        for (Theme theme : themes) {
            result.put(theme.getThemeName(), new HashMap<>(16));
        }
        // 向数据格式中添加数据
        for (NumberOfPublication data : nopData) {
            result.get(data.getThemeName()).put(data.getThesisYear(), data.getNum());
        }
        return ResultPackUtils.packDataMapToDataList(result);
    }

    /**
     * 获取饼图数据
     *
     * @return
     */
    @Override
    public List<DofVo> getDofData() {
        return ResultPackUtils.packDofList(statisticsMapper.getDistributionOfField());
    }
}
