package cn.chentyit.sdprms.service;

import java.util.Map;

/**
 * @Author Chentyit
 * @Date 2020/4/15 09:47
 * @Description:
 */
public interface OverviewService {

    /**
     * 获取 学者数量 || 论文数量 || 机构数量 || 论文主题数量 的汇总数据
     * @return 返回存储对应数据的 Map 集合
     */
    Map<String, Integer> getSummaryData();
}
