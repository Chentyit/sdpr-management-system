package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.dto.ThesisDTO;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.vo.ThesisVo;
import cn.chentyit.sdprms.service.ThemeService;
import cn.chentyit.sdprms.service.ThesisService;
import cn.chentyit.sdprms.util.FileUtils;
import cn.chentyit.sdprms.util.ResultPackUtils;
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
import java.util.List;

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

    @GetMapping("/thesis")
    public ModelAndView thesisPage() {
        ModelAndView modelAndView = new ModelAndView();

        // 获取所有论文数据
        List<Thesis> thesisList = thesisService.getAllThesis();
        modelAndView.setViewName("thesis/thesis");
        modelAndView.addObject("thesisList", thesisList);
        return modelAndView;
    }

    @PostMapping("/thesis/delete")
    @ResponseBody
    public String deleteByIds(@RequestBody List<String> ids) {
        log.info("删除论文信息 ID：" + ids);
        System.out.println(ids);
//        int rows = thesisService.deleteThesisById(ids);
        if (thesisService.removeByIds(ids)) {
            return "redirect:/thesis";
        } else {
            return "删除失败";
        }
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
            return "修改或添加失败";
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

    @PostMapping("/thesis/upload")
    public String uploadFile(@RequestParam("multipartFile") MultipartFile multipartFile) {
        log.info("multipartFiles ===== " + multipartFile.getOriginalFilename());
        try {
            List<Thesis> thesisList = FileUtils.resolveMulFileToBibObj(multipartFile);
            thesisService.saveBatch(thesisList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/thesis";
    }
}
