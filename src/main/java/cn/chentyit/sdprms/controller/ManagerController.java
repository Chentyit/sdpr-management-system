package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.dto.ManagerDTO;
import cn.chentyit.sdprms.model.entity.Manager;
import cn.chentyit.sdprms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author Chentyit
 * @Date 2020/4/13 16:28
 * @Description:
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/login")
    public String login(HttpSession session, ManagerDTO managerDTO) {
        Manager manager = managerService.login(managerDTO);
        if (manager != null) {
            session.setAttribute("manager", manager);
            return "main/recoverpw";
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

    @PostMapping("/recoverpw")
    public ModelAndView recoverPwd(HttpSession session, ManagerDTO managerDTO) {
        // 先从 Session 中获取用户名和用户 id
        Manager manager = (Manager) session.getAttribute("manager");
        // 验证用户在数据库中是否存在
        // 若存在则进入注册页面重新修改密码
        return null;
    }
}
