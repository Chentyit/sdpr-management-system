package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.entity.ThesisScholar;
import cn.chentyit.sdprms.model.vo.ThesisVo;
import cn.chentyit.sdprms.service.ScholarService;
import cn.chentyit.sdprms.service.ThemeService;
import cn.chentyit.sdprms.service.ThesisScholarService;
import cn.chentyit.sdprms.service.ThesisService;
import cn.chentyit.sdprms.util.FileUtils;
import cn.chentyit.sdprms.util.ResultPackUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:07
 * @Description: 论文数据控制层
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThesisController {

    private final ThesisService thesisService;
    private final ThemeService themeService;
    private final ScholarService scholarService;
    private final ThesisScholarService thesisScholarService;

    @GetMapping("/thesis")
    public ModelAndView thesisPage() {
        ModelAndView modelAndView = new ModelAndView();

        // 获取所有论文数据
        List<Thesis> thesisList = thesisService.getAllThesis();
        modelAndView.setViewName("thesis/thesis");
        modelAndView.addObject("thesisList", thesisList);
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/thesis/delete")
    public Map<String, Boolean> deleteByIds(@RequestBody List<String> ids) {
        log.info("删除论文信息 ID：" + ids);
        Map<String, Boolean> res = new HashMap<>(2);
        res.put("flag", thesisService.removeByIds(ids));
        return res;
    }

    @GetMapping("/thesis-details/{thesisId}")
    public ModelAndView thesisDetails(@PathVariable("thesisId") String thesisId) {
        ModelAndView modelAndView = new ModelAndView();
        Thesis thesis = thesisService.getById(thesisId);
        // 判断是否数据库中是否有该记录
        if (thesis != null) {
            // 将数据库映射对象转化为 Vo 视图对象
            ThesisVo thesisVo = ThesisVo.builder()
                    .thesisId(thesis.getThesisId())
                    .thesisTitle(thesis.getThesisTitle())
                    .themeId(thesis.getThemeId())
                    .thesisAuthor(thesis.getThesisAuthor())
                    .thesisDigest(thesis.getThesisDigest())
                    .thesisClassic(thesis.getThesisClassic())
                    .thesisBooktitle(thesis.getThesisBooktitle())
                    .thesisOrganization(thesis.getThesisOrganization())
                    .thesisPublisher(thesis.getThesisPublisher())
                    .thesisJournal(thesis.getThesisJournal())
                    .thesisVolume(thesis.getThesisVolume())
                    .thesisNumber(thesis.getThesisNumber())
                    .thesisPages(thesis.getThesisPages())
                    .thesisYear(thesis.getThesisYear())
                    .thesisDoi(thesis.getThesisDoi())
                    .thesisBibtex(thesis.getThesisBibtex())
                    .build();
            modelAndView.addObject("thesisVo", thesisVo);
        } else {
            modelAndView.addObject("thesisVo", new ThesisVo());
        }
        // 获取所有主题
        modelAndView.addObject("themes", themeService.getAllTheme());
        modelAndView.setViewName("thesis/thesis-details");
        return modelAndView;
    }

    @PostMapping("/thesis-detail")
    public String updateOrInsertThesis(ThesisDTO thesisDTO) {
        // 先删除数据库中的关联数据
        thesisScholarService.deleteItem(thesisDTO.getThesisId());

        // 解析学者字段
        List<String> authorNames = Splitter.on(" and ")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(thesisDTO.getThesisAuthor());

        // 获取保存的学者 ID
        List<Integer> ids = scholarService.findScholarByName(authorNames);

        // 向关联表中插入数据
        List<ThesisScholar> thesisScholars = new ArrayList<>();
        ids.forEach(scholarId -> {
            thesisScholars.add(ThesisScholar.builder().thesisId(thesisDTO.getThesisId()).scholarId(scholarId).build());
        });
        thesisScholarService.saveBatch(thesisScholars);

        Thesis thesis = Thesis.builder()
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
        if (thesisService.saveOrUpdate(thesis)) {
            return "redirect:/thesis";
        } else {
            return "修改或添加论文信息失败";
        }
    }

    @GetMapping("/thesis/download/{fileName}")
    public void downloadFile(
            HttpServletResponse response,
            @PathVariable("fileName") String fileName) throws IOException {
        List<Thesis> allThesis = thesisService.getAllThesis();
        String thesisJson = new Gson().toJson(allThesis);

        ResultPackUtils.downloadJsonFile(response, fileName, thesisJson);
    }

    @ResponseBody
    @PostMapping("/thesis/upload")
    public Map<String, Boolean> uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile) {
        log.info("获取到上传文件 ===== " + multipartFile.getOriginalFilename());
        Map<String, Boolean> res = new HashMap<>();

        try {
            // 保存论文信息
            Map<String, Object> result = FileUtils.resolveMulFileToBibObj(multipartFile);
            if (thesisService.saveBatch((List<Thesis>) result.get("thesisArrayList"), 100)) {
                log.info("论文数据插入成功");
            } else {
                log.error("论文数据插入失败");
            }

            // 保存学者信息以及学者和论文信息关联数据
            Map<String, List<Scholar>> scholarOfThesis = (Map<String, List<Scholar>>) result.get("scholarOfThesis");
            Set<Map.Entry<String, List<Scholar>>> entries = scholarOfThesis.entrySet();
            // 获取 thesisId 和 Scholar 数据
            entries.forEach(entry -> {
                String thesisId = entry.getKey();
                List<Scholar> scholarList = entry.getValue();
                scholarList.forEach(scholar -> {
                    LambdaUpdateWrapper<Scholar> scholarWrapper = new LambdaUpdateWrapper<>();
                    scholarWrapper.eq(Scholar::getScholarName, scholar.getScholarName());

                    // 只有学者数据插入成功，才能开始添加关联表
                    if (scholarService.saveOrUpdate(scholar, scholarWrapper)) {
                        log.info("学者信息插入成功");

                        ThesisScholar thesisScholar = ThesisScholar.builder()
                                .thesisId(thesisId)
                                .scholarId(scholarService.getOne(scholarWrapper).getScholarId())
                                .build();

                        // 添加关联数据
                        if (thesisScholarService.save(thesisScholar)) {
                            log.info("关联数据插入成功");
                        } else {
                            log.error("关联数据插入失败");
                        }
                    } else {
                        log.error("学者信息已存在，插入失败");
                    }
                });
            });
            res.put("flag", true);
        } catch (IOException e) {
            e.printStackTrace();
            res.put("flag", false);
        }
        return res;
    }
}
