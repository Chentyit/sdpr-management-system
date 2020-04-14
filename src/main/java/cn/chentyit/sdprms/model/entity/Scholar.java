package cn.chentyit.sdprms.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Chentyit
 * @Date 2020/4/14 11:24
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("scholar")
public class Scholar implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(value = "scholar_id")
    private int scholarId;

    @TableField(value = "scholar_name")
    private String scholarName;

    @TableField(value = "scholar_last_name")
    private String scholarLastName;

    @TableField(value = "scholar_country")
    private String scholarCountry;

    @TableField(value = "institute_id")
    private String instituteId;
}
