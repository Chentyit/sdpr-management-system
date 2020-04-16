package cn.chentyit.sdprms.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/16 11:55
 * @Description: 数据库查询出来的领域分布映射类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributionOfField {

    private int themeId;

    private String themeName;

    private int num;
}
