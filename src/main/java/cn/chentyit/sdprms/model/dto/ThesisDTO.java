package cn.chentyit.sdprms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Chentyit
 * @Date 2020/4/17 11:49
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThesisDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String thesisId;

    private String thesisTitle;

    private int themeId;

    private String thesisAuthor;

    private String thesisDigest;

    private int thesisClassic;

    private String thesisBooktitle;

    private String thesisOrganization;

    private String thesisPublisher;

    private String thesisJournal;

    private int thesisVolume;

    private String thesisNumber;

    private String thesisPages;

    private String thesisYear;

    private String thesisDoi;

    private String thesisBibtex;

    private LocalDateTime thesisUpdateTime;
}
