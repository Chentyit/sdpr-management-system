package cn.chentyit.sdprms;

import cn.chentyit.sdprms.dao.ThesisMapper;
import cn.chentyit.sdprms.service.ThesisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Author Chentyit
 * @Date 2020/4/16 15:33
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThesisApiTest {

    @Resource
    private ThesisMapper thesisMapper;

    @Resource
    private ThesisService thesisService;

    @Test
    public void getAllThesis() {
        thesisMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void deleteThesisByIds() {
        System.out.println(thesisService.deleteThesisById(Arrays.asList(666, 667, 668)));
    }
}
