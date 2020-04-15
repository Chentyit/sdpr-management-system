package cn.chentyit.sdprms.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Chentyit
 * @Date 2020/4/15 16:43
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberOfPublication {

    private String themeName;

    private String thesisYear;

    private int num;
}
