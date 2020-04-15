package cn.chentyit.sdprms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Chentyit
 * @Date 2020/4/15 08:50
 * @Description:
 */
@Controller
public class OverViewController {

    @GetMapping("/overview")
    public String overview() {
        return "overview/overview";
    }
}
