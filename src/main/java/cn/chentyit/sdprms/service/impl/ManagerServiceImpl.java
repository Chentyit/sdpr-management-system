package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ManagerMapper;
import cn.chentyit.sdprms.model.dto.ManagerDTO;
import cn.chentyit.sdprms.model.entity.Manager;
import cn.chentyit.sdprms.service.ManagerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Chentyit
 * @Date 2020/4/14 13:37
 * @Description:
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private ManagerMapper managerMapper;

    @Resource
    private LambdaQueryWrapper<Manager> lambdaQuery;

    @Resource
    private LambdaUpdateWrapper<Manager> lambdaUpdate;

    @Override
    public Manager login(ManagerDTO managerDTO) {
        lambdaQuery.select(Manager::getManagerId, Manager::getManagerName)
                .eq(Manager::getManagerName, "root")
                .and(lqw -> lqw.eq(Manager::getManagerPassword, "root"));
        return managerMapper.selectOne(lambdaQuery);
    }

    @Override
    public int register(ManagerDTO managerDTO) {
        Manager manager = Manager.builder()
                .managerName(managerDTO.getManagerName())
                .managerEmail(managerDTO.getManagerEmail())
                .managerPassword(managerDTO.getManagerPassword())
                .build();
        return managerMapper.insert(manager);
    }

    @Override
    public int recoverPwd(int id, ManagerDTO managerDTO) {
        // 先通过管理员姓名查出原数据信息
        lambdaQuery.select(Manager::getManagerId, Manager::getManagerName)
                .like(Manager::getManagerName, managerDTO.getManagerName());
        Manager manager = managerMapper.selectOne(lambdaQuery);

        // 判断登录的当前用户 ID 是否和数据库一致
        if (manager.getManagerId() == id) {
            // 将新的密码覆盖原来的密码
            lambdaUpdate.eq(Manager::getManagerId, manager.getManagerId())
                    .eq(Manager::getManagerName, manager.getManagerName())
                    .set(Manager::getManagerPassword, managerDTO.getManagerPassword());
            return managerMapper.update(null, lambdaUpdate);
        } else {
            return 0;
        }
    }
}
