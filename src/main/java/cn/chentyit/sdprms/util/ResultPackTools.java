package cn.chentyit.sdprms.util;

import cn.chentyit.sdprms.model.vo.NopVo;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Chentyit
 * @Date 2020/4/15 17:52
 * @Description:
 */
public class ResultPackTools {

    /**
     * {
     *  "A": {
     *  "2013": 1,
     *  "2017": 1,
     *  "2018": 1
     *  },
     *  "B": {
     *  "2016": 1
     *  },
     *  "C": {
     *  "2013": 1
     *  },
     *  "D": {
     *  "2018": 1
     *  }
     * }
     *
     * @param data
     * @return
     */
    public static List<Object> packMapToList(Map<String, Map<String, Integer>> data) {
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
}
