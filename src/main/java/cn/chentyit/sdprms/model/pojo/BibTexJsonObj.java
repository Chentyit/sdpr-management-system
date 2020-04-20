package cn.chentyit.sdprms.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/19 17:58
 * @Description: 用于映射
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BibTexJsonObj {

    private String type;

    private String id;

    private String citekey;

    private String collection;

    private String title;

    private String year;

    private String booktitle;

    private String organization;

    private String pages;

    private List<Author> author;

    private Journal journal;
}
