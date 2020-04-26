package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.entity.Theme;
import cn.chentyit.sdprms.model.vo.ThemeVo;
import cn.chentyit.sdprms.service.ThemeService;
import cn.chentyit.sdprms.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Chentyit
 * @Date 2020/4/25 18:57
 * @Description:
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThemeController {

    private final ThemeService themeService;
    private final ThesisService thesisService;

    /**
     * 获取论文主题的数据
     *
     * @return
     */
    @GetMapping("/theme")
    public ModelAndView themePage() {
        // 创建模型视图
        ModelAndView modelAndView = new ModelAndView();
        // 获取所有主题
        List<Theme> themes = themeService.getAllTheme();
        // 获取某个主题下的论文数量
        List<ThemeVo> themeVos = new ArrayList<>();
        themes.forEach(theme -> {
            ThemeVo themeVo = ThemeVo.builder()
                    .themeId(theme.getThemeId())
                    .themeName(theme.getThemeName())
                    .thesisNum(thesisService.getTheNumberOfThesisOnTheme(theme.getThemeId()))
                    .build();
            themeVos.add(themeVo);
        });

        // 设置视图
        modelAndView.setViewName("theme/theme");
        // 设置携带数据
        modelAndView.addObject("themeVos", themeVos);
        return modelAndView;
    }

    /**
     * 根据主题 ID 删除主题数据
     *
     * @param themeId
     * @return
     */
    @ResponseBody
    @GetMapping("/theme/delete/{themeId}")
    public Map<String, Boolean> deleteTheme(@PathVariable("themeId") Integer themeId) {
        Map<String, Boolean> res = new HashMap<>(2);
        res.put("flag", themeService.removeById(themeId));
        return res;
    }

    @PostMapping("/theme/add")
    public String saveTheme(String themeName) {
        Theme theme = Theme.builder().themeName(themeName).build();
        themeService.save(theme);
        return "redirect:/theme";
    }
}
