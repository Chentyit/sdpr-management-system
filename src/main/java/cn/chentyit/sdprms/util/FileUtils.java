package cn.chentyit.sdprms.util;

import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.pojo.BibTexJsonObj;
import cn.chentyit.sdprms.model.pojo.Journal;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Chentyit
 * @Date 2020/4/19 17:46
 * @Description:
 */
@Slf4j
public class FileUtils {

    /**
     * 将 json 文件解析成对象
     *
     * @param multipartFile
     * @throws IOException
     */
    public static void resolveMulFile(MultipartFile multipartFile) throws IOException {
        // 使用字符流包装字节流
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(multipartFile.getBytes()), StandardCharsets.UTF_8));
        // 获取文件中的信息
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        br.close();

        // 将读取到的 json 数据转换成对象
        JsonObject asJsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
        // 这里只需要列表里面的数据
        JsonElement records = asJsonObject.get("records");
        Gson gson = new Gson();

        // 将列表里面的数据转化为数据库映射 entity 对象
        List<Thesis> thesisList = bibObjToThesis(gson.fromJson(records.toString(), new TypeToken<List<BibTexJsonObj>>() {
        }.getType()));
    }

    /**
     * 将解析出来的对象存储到数据库中
     * <p>
     * {
     * "type": "article",
     * "id": "bennin2018mahakil",
     * "citekey": "bennin2018mahakil",
     * "collection": "prediction",
     * "title": "Mahakil: Diversity based oversampling approach to alleviate the class imbalance issue in software defect prediction",
     * "year": "2018",
     * "author": [
     * {
     * "name": "Bennin, Kwabena Ebo"
     * },
     * {
     * "name": "Keung, Jacky"
     * },
     * {
     * "name": "Phannachitta, Passakorn"
     * },
     * {
     * "name": "Monden, Akito"
     * },
     * {
     * "name": "Mensah, Solomon"
     * }
     * ],
     * "journal": {
     * "name": "IEEE Transactions on Software Engineering (TSE)",
     * "volume": "44",
     * "number": "6",
     * "pages": "534--550"
     * }
     * },
     * {
     * "type": "inproceedings",
     * "id": "wang2016automatically",
     * "citekey": "wang2016automatically",
     * "collection": "prediction",
     * "title": "Automatically learning semantic features for defect prediction",
     * "year": "2016",
     * "booktitle": "Software Engineering (ICSE), 2016 IEEE/ACM 38th International Conference on",
     * "organization": "IEEE",
     * "pages": "297--308",
     * "author": [
     * {
     * "name": "Wang, Song"
     * },
     * {
     * "name": "Liu, Taiyue"
     * },
     * {
     * "name": "Tan, Lin"
     * }
     * ]
     * },
     *
     * @param bibTexJsonObjs
     */
    private static List<Thesis> bibObjToThesis(List<BibTexJsonObj> bibTexJsonObjs) {
        List<Thesis> thesisArrayList = new ArrayList<>();
        bibTexJsonObjs.forEach(bibTexJsonObj -> {
            Thesis thesis = Thesis.builder().build();

            thesis.setThesisId(bibTexJsonObj.getId());
            thesis.setThesisTitle(bibTexJsonObj.getTitle());
            thesis.setThemeId(0);

            // 保存作者信息
            StringBuilder sb = new StringBuilder();
            bibTexJsonObj.getAuthor().forEach(author -> {
                sb.append(author.getName()).append(" and ");
            });
            String authors = sb.toString();
            thesis.setThesisAuthor(authors.substring(0, authors.length() - 5));

            thesis.setThesisDigest(bibTexJsonObj.getCollection());

            // 设置论文类别
            if (bibTexJsonObj.getType().equals("inproceedings")) {
                thesis.setThesisClassic(0);
            } else if (bibTexJsonObj.getType().equals("article")) {
                thesis.setThesisClassic(1);
            } else {
                thesis.setThesisClassic(2);
            }

            thesis.setThesisBooktitle(bibTexJsonObj.getBooktitle());
            thesis.setThesisOrganization(bibTexJsonObj.getOrganization());

            // 设置期刊名称
            Journal journal = bibTexJsonObj.getJournal();
            if (Objects.isNull(journal)) {
                thesis.setThesisPublisher(journal.getName());
                // 设置期刊类别
                String journal_name = bibTexJsonObj.getJournal().getName();
                if (journal_name.contains("(") && journal_name.contains(")")) {
                    String journal_classic = journal_name.substring(
                            journal_name.indexOf("("),
                            journal_name.indexOf(")"));
                    thesis.setThesisJournal(journal_classic);
                }
                // 设置期刊卷号
                thesis.setThesisVolume(Integer.parseInt(journal.getVolume()));
                // 设置期刊
                thesis.setThesisNumber(Integer.parseInt(journal.getNumber()));
                // 设置论文页码
                thesis.setThesisPages(journal.getPages());
            } else {
                thesis.setThesisPages(bibTexJsonObj.getPages());
            }

            thesis.setThesisYear(bibTexJsonObj.getYear());
            thesis.setThesisDoi("");
            thesis.setThesisBibtex("");
            thesis.setThesisCreateTime(LocalDateTime.now());
            thesis.setThesisUpdateTime(LocalDateTime.now());

            thesisArrayList.add(thesis);
        });

        return thesisArrayList;
    }
}
