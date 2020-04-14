package cn.chentyit.sdprms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Chentyit
 * @Date 2020/4/14 10:55
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("thesis")
public class Thesis implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(value = "thesis_id", type = IdType.AUTO)
    private int thesisId;

    @TableField(value = "thesis_title")
    private String thesisTitle;

    @TableField(value = "theme_id")
    private int themeId;

    @TableField(value = "thesis_author")
    private String thesisAuthor;

    @TableField(value = "thesis_digest")
    private String thesisDigest;

    @TableField(value = "thesis_classic")
    private int thesisClassic;

    @TableField(value = "thesis_booktitle")
    private String thesisBooktitle;

    @TableField(value = "thesis_organization")
    private String thesisOrganization;

    @TableField(value = "thesis_publisher")
    private String thesisPublisher;

    @TableField(value = "thesis_journal")
    private String thesisJournal;

    @TableField(value = "thesis_volume")
    private int thesisVolume;

    @TableField(value = "thesis_number")
    private int thesisNumber;

    @TableField(value = "thesis_pages")
    private String thesisPages;

    @TableField(value = "thesis_year")
    private String thesisYear;

    @TableField(value = "thesis_doi")
    private String thesisDoi;

    @TableField(value = "thesis_bibtex")
    private String thesisBibtex;

    @TableField(value = "thesis_create_time")
    private LocalDateTime thesisCreateTime;

    @TableField(value = "thesis_update_time")
    private LocalDateTime thesisUpdateTime;
}
