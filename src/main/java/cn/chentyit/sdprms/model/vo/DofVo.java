package cn.chentyit.sdprms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/16 11:43
 * @Description: 研究领域视图对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DofVo {

    private int themeId;

    private String themeName;

    private int num;

    private int percentage;
}
