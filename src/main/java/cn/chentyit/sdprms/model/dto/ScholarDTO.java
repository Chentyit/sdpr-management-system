package cn.chentyit.sdprms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/24 10:54
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScholarDTO {

    private int scholarId;

    private String scholarName;

    private String scholarCountry;
}
