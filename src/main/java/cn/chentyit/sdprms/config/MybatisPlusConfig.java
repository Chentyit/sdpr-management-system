package cn.chentyit.sdprms.config;

import cn.chentyit.sdprms.dao.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public QueryWrapper<T> getWrapper() {
        return new QueryWrapper<>();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LambdaQueryWrapper<T> getLambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UpdateWrapper<T> getUpdateWrapper() {
        return new UpdateWrapper<>();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LambdaUpdateWrapper<T> getLambdaUpdateWrapper() {
        return Wrappers.lambdaUpdate();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
