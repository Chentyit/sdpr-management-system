package cn.chentyit.sdprms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Chentyit
 * @Date 2020/4/14 10:17
 * @Description:
 */
public class SdprMSLoginHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("manager");
        if (user != null) {
            return true;
        }

        request.setAttribute("loginMsg", "没有权限");
        request.getRequestDispatcher("/index").forward(request, response);
        return false;
    }
}
