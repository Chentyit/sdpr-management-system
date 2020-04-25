package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ScholarMapper;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.service.ScholarService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Chentyit
 * @Date 2020/4/20 17:52
 * @Description:
 */
@Service
public class ScholarServiceImpl extends ServiceImpl<ScholarMapper, Scholar> implements ScholarService {

    @Resource
    private ScholarMapper scholarMapper;

    @Override
    public List<Scholar> getAllScholar() {
        return scholarMapper.selectList(null);
    }

    @Override
    public List<Integer> findScholarByName(List<String> authorNames) {
        List<Integer> ids = new ArrayList<>();
        // 遍历学者名
        authorNames.forEach(name -> {
            // 从数据库中查询每个学者对应的 ID
            LambdaQueryWrapper<Scholar> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.select(Scholar::getScholarId).eq(Scholar::getScholarName, name);
            Scholar scholar = scholarMapper.selectOne(lambdaQueryWrapper);
            // 若学者不存在则插入新数据
            if (Objects.isNull(scholar)) {
                scholar = Scholar.builder()
                        .scholarName(name)
                        .scholarLastName(name.split(" ")[1].trim())
                        .build();
                scholarMapper.insert(scholar);
            }
            // 记录下学者的 ID
            ids.add(scholar.getScholarId());
        });
        return ids;
    }
}
