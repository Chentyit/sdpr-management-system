package cn.chentyit.sdprms.controller;

import cn.chentyit.sdprms.model.dto.ScholarDTO;
import cn.chentyit.sdprms.model.entity.Scholar;
import cn.chentyit.sdprms.model.entity.Thesis;
import cn.chentyit.sdprms.model.vo.ScholarVo;
import cn.chentyit.sdprms.service.ScholarService;
import cn.chentyit.sdprms.util.ResultPackUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author Chentyit
 * @Date 2020/4/24 08:48
 * @Description: 学者控制层
 */
@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScholarController {

    private final ScholarService scholarService;

    /**
     * 进入学者列表主页
     *
     * @return
     */
    @GetMapping("/scholar")
    public ModelAndView scholarPage() {
        ModelAndView modelAndView = new ModelAndView();
        // 保存显示到页面的数据
        List<ScholarVo> scholarVos = new ArrayList<>();
        // 从数据库获取数据存入容器中发送到页面上
        scholarService.getAllScholar().forEach(scholar -> {
            ScholarVo scholarVo = ScholarVo.builder()
                    .scholarId(scholar.getScholarId())
                    .scholarName(scholar.getScholarName())
                    .scholarCountry(scholar.getScholarCountry())
                    .build();
            scholarVos.add(scholarVo);
        });
        // 设置模型视图
        modelAndView.setViewName("scholar/scholar");
        modelAndView.addObject("scholarVos", scholarVos);

        return modelAndView;
    }

    /**
     * 批量删除学者信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping("/scholar/delete")
    public Map<String, Boolean> deleteScholarList(@RequestBody List<String> ids) {
        log.info("删除学者信息 ID：" + ids);
        Map<String, Boolean> res = new HashMap<>(2);
        res.put("flag", scholarService.removeByIds(ids));
        return res;
    }

    /**
     * 下载学者信息 JSON 文件
     *
     * @param response
     * @param fileName
     * @throws IOException
     */
    @GetMapping("/scholar/download/{fileName}")
    public void downloadFile(
            HttpServletResponse response,
            @PathVariable("fileName") String fileName) throws IOException {
        List<Scholar> scholars = scholarService.getAllScholar();
        String scholarJson = new Gson().toJson(scholars);
        ResultPackUtils.downloadJsonFile(response, fileName, scholarJson);
    }

    /**
     * 进入学者详细信息修改页面
     *
     * @param scholarId
     * @return
     */
    @GetMapping("/scholar-detail/{scholarId}")
    public ModelAndView scholarDetail(@PathVariable("scholarId") int scholarId) {
        ModelAndView modelAndView = new ModelAndView();
        Scholar scholar = scholarService.getById(scholarId);
        if(!Objects.isNull(scholar)) {
            ScholarVo scholarVo = ScholarVo.builder()
                    .scholarId(scholar.getScholarId())
                    .scholarName(scholar.getScholarName())
                    .scholarCountry(scholar.getScholarCountry())
                    .build();
            modelAndView.addObject("scholarVo", scholarVo);
        } else {
            modelAndView.addObject("scholarVo", new ScholarVo());
        }
        modelAndView.setViewName("scholar/scholar-details");
        return modelAndView;
    }

    @PostMapping("/scholar-detail")
    public String updateOrSaveScholar(ScholarDTO scholarDTO) {
        Scholar scholar = Scholar.builder()
                .scholarId(scholarDTO.getScholarId())
                .scholarName(scholarDTO.getScholarName().trim())
                .scholarLastName(scholarDTO.getScholarName().split(" ")[1].trim())
                .scholarCountry(scholarDTO.getScholarCountry())
                .build();
        if (scholarService.saveOrUpdate(scholar)) {
            return "redirect:/scholar";
        } else {
            return "修改或添加学者信息失败";
        }
    }
}
