package cn.chentyit.sdprms.util;

import cn.chentyit.sdprms.model.pojo.DistributionOfField;
import cn.chentyit.sdprms.model.vo.DofVo;
import cn.chentyit.sdprms.model.vo.NopVo;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Chentyit
 * @Date 2020/4/15 17:52
 * @Description:
 */
public class ResultPackTools {

    /**
     * {
     * "A": {
     * "2013": 1,
     * "2017": 1,
     * "2018": 1
     * },
     * "B": {
     * "2016": 1
     * },
     * "C": {
     * "2013": 1
     * },
     * "D": {
     * "2018": 1
     * }
     * }
     *
     * @param data
     * @return
     */
    public static List<Object> packDataMapToDataList(Map<String, Map<String, Integer>> data) {
        List<Object> result = new ArrayList<>();

        /*
        这一步获取一个主题的数据
         {
           "A": {
           "2013": 1,
           "2017": 1,
           "2018": 1
           }
         }
         */
        for (Map.Entry<String, Map<String, Integer>> entry : data.entrySet()) {
            List<Integer> nums = new ArrayList<>();

            /*
            这一步获取主题中对应的数据
            {
               "2013": 1,
               "2017": 1,
               "2018": 1
            }
             */
            int endYear = LocalDateTime.now().getYear();
            int startYear = endYear - 9;
            for (int i = startYear; i <= endYear; i++) {
                Integer num = entry.getValue().get(String.valueOf(i));
                if (num != null) {
                    nums.add(num);
                } else {
                    nums.add(0);
                }
            }

            result.add(NopVo.builder().themeName(entry.getKey()).nums(nums).build());
        }
        return result;
    }

    /**
     * 将数据库中查询出来的数据转化为 VO 视图对象
     * 数据库中查询出来的值没有百分比，在此计算百分比并存入集合
     *
     * @param data 数据库中查询出来的数据
     * @return
     */
    public static List<DofVo> packDofList(List<DistributionOfField> data) {
        AtomicInteger sum = new AtomicInteger();
        List<DofVo> result = new ArrayList<>();

        // 第一次遍历存储基础数据
        data.forEach(dof -> {
            DofVo dofVo = DofVo.builder()
                    .themeId(dof.getThemeId())
                    .themeName(dof.getThemeName())
                    .num(dof.getNum())
                    .percentage(0)
                    .build();
            sum.addAndGet(dof.getNum());
            result.add(dofVo);
        });

        // 计算每个主题的百分比
        final int finalSum = sum.get();
        result.forEach(dofVo -> dofVo.setPercentage(dofVo.getNum() / finalSum));

        return result;
    }

    /**
     * 下载 Json 文件
     */
    public static void downloadJsonFile(HttpServletResponse response, String fileName, String jsonStr) throws IOException {

        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置 Content-type，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String code = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + code);

        // 创建字符流（尝试过字节流，但是字节流会丢失数据，就改用字符流了）
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(jsonStr.getBytes())));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        String line = br.readLine();

        while (line != null) {
            bw.write(line);
            line = br.readLine();
        }

        // 关闭流
        br.close();
        bw.close();
    }
}
