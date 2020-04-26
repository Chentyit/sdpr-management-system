package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.entity.Theme;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/17 14:17
 * @Description:
 */
public interface ThemeService extends IService<Theme> {

    /**
     * 获取所有主题
     *
     * @return
     */
    List<Theme> getAllTheme();
}
