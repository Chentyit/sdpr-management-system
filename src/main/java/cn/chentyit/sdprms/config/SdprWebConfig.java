package cn.chentyit.sdprms.config;

import cn.chentyit.sdprms.handler.MetaHandler;
import cn.chentyit.sdprms.interceptor.SdprMSLoginHandleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Chentyit
 * @Date 2020/4/14 10:13
 * @Description:
 */
@Configuration
public class SdprWebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // 登录
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");

                // 注册
                registry.addViewController("/register.html").setViewName("main/register");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new SdprMSLoginHandleInterceptor())
                        .addPathPatterns("/**")
                        // 登录页面路径
                        .excludePathPatterns("/", "/index", "/index.html")
                        // 注册页面路径
                        .excludePathPatterns("/register.html")
                        // 重设密码路径
                        .excludePathPatterns("/recoverpw.html")
                        // 登录，注册，重置密码请求
                        .excludePathPatterns("/login", "/register", "/recoverpw")
                        .excludePathPatterns("/css/*", "/js/*", "/fonts/*", "/images/*");
            }
        };
    }
}
