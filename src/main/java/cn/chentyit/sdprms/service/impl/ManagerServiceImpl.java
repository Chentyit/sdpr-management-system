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

    @Override
    public Manager login(ManagerDTO managerDTO) {
        LambdaQueryWrapper<Manager> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.select(Manager::getManagerId, Manager::getManagerName)
                .eq(Manager::getManagerName, managerDTO.getManagerName())
                .and(lqw -> lqw.eq(Manager::getManagerPassword, managerDTO.getManagerPassword()));
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
    public String recoverPwd(ManagerDTO managerDTO) {
        LambdaQueryWrapper<Manager> lambdaQuery = new LambdaQueryWrapper<>();
        LambdaUpdateWrapper<Manager> lambdaUpdate = new LambdaUpdateWrapper<>();
        // 先通过管理员姓名查出原数据信息
        lambdaQuery.select(Manager::getManagerName, Manager::getManagerEmail)
                .like(Manager::getManagerEmail, managerDTO.getManagerEmail());
        Manager manager = managerMapper.selectOne(lambdaQuery);

        // 判断当前用户邮箱是否和数据库一致
        if (manager.getManagerEmail().equals(managerDTO.getManagerEmail())) {
            // 将新的密码覆盖原来的密码
            lambdaUpdate.eq(Manager::getManagerName, manager.getManagerName())
                    .eq(Manager::getManagerEmail, managerDTO.getManagerEmail())
                    .set(Manager::getManagerPassword, managerDTO.getManagerPassword());
            managerMapper.update(null, lambdaUpdate);
            return manager.getManagerName();
        } else {
            return null;
        }
    }
}
