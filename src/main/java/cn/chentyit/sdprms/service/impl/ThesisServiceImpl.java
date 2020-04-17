package cn.chentyit.sdprms.service.impl;

import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.vo.ThesisVo;
import cn.chentyit.sdprms.service.ThesisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:31
 * @Description:
 */
@Service
public class ThesisServiceImpl implements ThesisService {

    @Resource
    private ThesisMapper thesisMapper;

    @Override
    public List<Thesis> getAllThesis() {
        return thesisMapper.selectList(null);
    }

    @Override
    public int deleteThesisById(List<Integer> ids) {
        return thesisMapper.deleteBatchIds(ids);
    }

    @Override
    public Thesis findThesisById(int thesisId) {
        return thesisMapper.selectById(thesisId);
    }

    @Override
    public int saveOrUpdateThesis(ThesisDTO thesisDTO) {
        Thesis thesisBuf = Thesis.builder()
                .thesisId(thesisDTO.getThesisId())
                .thesisTitle(thesisDTO.getThesisTitle())
                .themeId(thesisDTO.getThemeId())
                .thesisAuthor(thesisDTO.getThesisAuthor())
                .thesisDigest(thesisDTO.getThesisDigest())
                .thesisClassic(thesisDTO.getThesisClassic())
                .thesisBooktitle(thesisDTO.getThesisBooktitle())
                .thesisOrganization(thesisDTO.getThesisOrganization())
                .thesisPublisher(thesisDTO.getThesisPublisher())
                .thesisJournal(thesisDTO.getThesisJournal())
                .thesisVolume(thesisDTO.getThesisVolume())
                .thesisNumber(thesisDTO.getThesisNumber())
                .thesisPages(thesisDTO.getThesisPages())
                .thesisYear(thesisDTO.getThesisYear())
                .thesisDoi(thesisDTO.getThesisDoi())
                .thesisBibtex(thesisDTO.getThesisBibtex())
                .thesisUpdateTime(LocalDateTime.now())
                .build();
        if (thesisMapper.selectById(thesisDTO.getThesisId()) != null) {
            return thesisMapper.updateById(thesisBuf);
        } else {
            thesisBuf.setThesisCreateTime(LocalDateTime.now());
            return thesisMapper.insert(thesisBuf);
        }
    }
}
