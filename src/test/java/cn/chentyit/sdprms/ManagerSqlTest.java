package cn.chentyit.sdprms;

import cn.chentyit.sdprms.dao.ManagerMapper;
import cn.chentyit.sdprms.model.entity.Manager;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author Chentyit
 * @Date 2020/4/14 11:51
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerSqlTest {

    @Resource
    private ManagerMapper managerMapper;

    @Resource
    private LambdaQueryWrapper<Manager> lambdaQuery;

    @Resource
    private LambdaUpdateWrapper<Manager> lambdaUpdate;

    @Test
    public void selectManagerByAccountAndPwd() {
        lambdaQuery.eq(Manager::getManagerName, "root").and(lqw -> lqw.eq(Manager::getManagerPassword, "root"));
        managerMapper.selectList(lambdaQuery).forEach(System.out::println);
    }

    @Test
    public void insertManager() {
        Manager manager = Manager.builder()
                .managerName("cty")
                .managerPassword("cty")
                .managerEmail("cty@qq.com")
                .build();
        int insert = managerMapper.insert(manager);
        System.out.println(insert);
    }

    @Test
    public void recoverPwd() {
        lambdaUpdate.eq(Manager::getManagerId, 666)
                .eq(Manager::getManagerName, "chentyit")
                .set(Manager::getManagerPassword, "chentyit123456");
        int update = managerMapper.update(null, lambdaUpdate);
        System.out.println(update);
    }

    @Test
    public void selectManagerByName() {
        lambdaQuery.select(Manager::getManagerId, Manager::getManagerName).like(Manager::getManagerName, "chen");
        Manager manager = managerMapper.selectOne(lambdaQuery);
        System.out.println(manager);
    }
}
