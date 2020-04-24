package cn.chentyit.sdprms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/24 09:16
 * @Description: 学者前端显示对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScholarVo {

    private int scholarId;

    private String scholarName;

    private String scholarCountry;
}
