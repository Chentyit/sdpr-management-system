package cn.chentyit.sdprms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/14 13:38
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {

    private String managerName;

    private String managerPassword;

    private String managerEmail;
}
