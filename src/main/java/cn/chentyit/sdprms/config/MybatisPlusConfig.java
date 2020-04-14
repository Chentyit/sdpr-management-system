package cn.chentyit.sdprms.config;

import cn.chentyit.sdprms.dao.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author Chentyit
 * @Date 2020/4/14 11:42
 * @Description:
 */
@Configuration
public class MybatisPlusConfig<T> {

    @Resource
    private ManagerMapper managerMapper;

    @Resource
    private ScholarMapper scholarMapper;

    @Resource
    private ThemeMapper themeMapper;

    @Resource
    private ThesisMapper thesisMapper;

    @Resource
    private ThesisScholarMapper thesisScholarMapper;

    @Bean
    public QueryWrapper<T> getWrapper() {
        return new QueryWrapper<>();
    }

    @Bean
    public LambdaQueryWrapper<T> getLambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    @Bean
    public UpdateWrapper<T> getUpdateWrapper() {
        return new UpdateWrapper<>();
    }

    @Bean
    public LambdaUpdateWrapper<T> getLambdaUpdateWrapper() {
        return Wrappers.lambdaUpdate();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
