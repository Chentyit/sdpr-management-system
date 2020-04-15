package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.dto.ManagerDTO;
import cn.chentyit.sdprms.model.entity.Manager;
import cn.chentyit.sdprms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author Chentyit
 * @Date 2020/4/13 16:28
 * @Description:
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/login")
    public String login(HttpSession session, ManagerDTO managerDTO) {
        Manager manager = managerService.login(managerDTO);
        if (manager != null) {
            session.setAttribute("manager", manager);
            return "redirect:/overview";
        } else {
            return "redirect:/index.html";
        }
    }

    @PostMapping("/register")
    public ModelAndView register(ManagerDTO managerDTO) {
        ModelAndView modelAndView = new ModelAndView();
        int register = managerService.register(managerDTO);
        if (register != 0) {
            modelAndView.setViewName("main/login");
            modelAndView.addObject("managerName", managerDTO.getManagerName());
        } else {
            modelAndView.setViewName("main/register");
        }
        return modelAndView;
    }

    @GetMapping("/recoverpw.html")
    public String recoverPwd(HttpSession session) {
        // 先清除 Session 中的数据，让用户登出系统
        session.removeAttribute("manager");
        session.invalidate();
        return "main/recoverpw";
    }

    @PostMapping("/recoverpw")
    public ModelAndView recoverPwd(ManagerDTO managerDTO) {
        ModelAndView modelAndView = new ModelAndView();
        // 验证用户在数据库中是否存在
        // 若存在则进入注册页面重新修改密码
        String managerName = managerService.recoverPwd(managerDTO);
        if (!StringUtils.isEmpty(managerName)) {
            log.error("重置密码成功");
            modelAndView.setViewName("main/login");
            modelAndView.addObject("managerName", managerName);
        } else {
            log.error("重置密码失败，检查用户是否存在");
            modelAndView.setViewName("main/recoverpw");
            modelAndView.addObject("recoverPwdMsg", "重置密码失败，检查用户是否存在");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("manager");
        session.invalidate();
        return "main/login";
    }
}
