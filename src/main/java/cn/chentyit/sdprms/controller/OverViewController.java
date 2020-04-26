package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.vo.DofVo;
import cn.chentyit.sdprms.service.OverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/15 08:50
 * @Description:
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OverViewController {

    private final OverviewService overviewService;

    @GetMapping("/overview")
    public ModelAndView overview() {
        // 进入页面后需要携带一些数据
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("overview/overview");
        modelAndView.addObject("summaryData", overviewService.getSummaryData());
        return modelAndView;
    }

    /**
     * 获取走势图数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/sta/getNopData")
    public List<Object> getNopData() {
        return overviewService.getNopData();
    }

    /**
     * 获取饼图数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/sta/getDofData")
    public List<DofVo> getDofVo() {
        return overviewService.getDofData();
    }
}
