package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ThemeMapper;
import cn.chentyit.sdprms.model.entity.Theme;
import cn.chentyit.sdprms.service.ThemeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/17 14:18
 * @Description:
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    @Resource
    private ThemeMapper themeMapper;

    @Override
    public List<Theme> getAllTheme() {
        return themeMapper.selectList(null);
    }
}
