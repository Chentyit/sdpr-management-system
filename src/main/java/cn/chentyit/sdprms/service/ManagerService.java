package cn.chentyit.sdprms.service;

import cn.chentyit.sdprms.model.dto.ManagerDTO;
import cn.chentyit.sdprms.model.entity.Manager;

/**
 * @Author Chentyit
 * @Date 2020/4/14 13:37
 * @Description:
 */
public interface ManagerService {

    /**
     * 用户登录服务
     * @param managerDTO
     * @return 用户存在返回 true，不存在放回 false
     */
    Manager login(ManagerDTO managerDTO);

    /**
     * 用户注册服务
     * @param managerDTO
     * @return 返回数据库改变数目
     */
    int register(ManagerDTO managerDTO);

    /**
     * 用户重置密码
     * @param id 从 Session 中获取的用户 id
     * @param managerDTO
     * @return
     */
    int recoverPwd(int id, ManagerDTO managerDTO);
}
