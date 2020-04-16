package cn.chentyit.sdprms.util;

import cn.chentyit.sdprms.model.pojo.DistributionOfField;
import cn.chentyit.sdprms.model.vo.DofVo;
import cn.chentyit.sdprms.model.vo.NopVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
}
