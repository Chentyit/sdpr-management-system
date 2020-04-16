package cn.chentyit.sdprms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author Chentyit
 * @Date 2020/4/15 17:55
 * @Description: 主题逐年数据视图对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NopVo {

    private String themeName;

    private List<Integer> nums;
}
