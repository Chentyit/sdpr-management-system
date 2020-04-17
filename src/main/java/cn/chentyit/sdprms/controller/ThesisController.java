package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:07
 * @Description: 论文数据控制层
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThesisController {

    private final ThesisService thesisService;

    @GetMapping("/thesis")
    public ModelAndView thesisPage() {
        ModelAndView modelAndView = new ModelAndView();

        // 获取所有论文数据
        List<Thesis> thesisList = thesisService.getAllThesis();
        modelAndView.setViewName("thesis/thesis");
        modelAndView.addObject("thesisList", thesisList);
        return modelAndView;
    }

    @PostMapping("/thesis/delete")
    @ResponseBody
    public String deleteByIds(@RequestBody List<Integer> ids) {
        log.info("删除论文信息 ID：" + ids);
        int rows = thesisService.deleteThesisById(ids);
        if (rows != 0) {
            return "redirect:/thesis";
        } else {
            return "删除失败";
        }
    }
}
