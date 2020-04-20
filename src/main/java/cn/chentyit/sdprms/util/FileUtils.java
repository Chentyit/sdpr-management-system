package cn.chentyit.sdprms.util;

import cn.chentyit.sdprms.model.pojo.BibTexJsonObj;
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
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/19 17:46
 * @Description:
 */
@Slf4j
public class FileUtils {

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
        List<BibTexJsonObj> bibTexJsonObjs = gson.fromJson(records.toString(), new TypeToken<List<BibTexJsonObj>>(){}.getType());
        bibTexJsonObjs.forEach(System.out::println);
    }
}
