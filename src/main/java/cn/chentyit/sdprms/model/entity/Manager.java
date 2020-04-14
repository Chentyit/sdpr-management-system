package cn.chentyit.sdprms.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Chentyit
 * @Date 2020/4/14 10:49
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("manager")
public class Manager implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "manager_id", type = IdType.AUTO)
    private int managerId;

    @TableField(value = "manager_name", condition = SqlCondition.LIKE)
    private String managerName;

    @TableField(value = "manager_password")
    private String managerPassword;

    @TableField(value = "manager_email")
    private String managerEmail;

    @TableField(value = "manager_permissions", fill = FieldFill.INSERT)
    private int managerPermissions;
}
