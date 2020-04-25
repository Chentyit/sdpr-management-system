package cn.chentyit.sdprms;

import cn.chentyit.sdprms.service.ThesisScholarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author Chentyit
 * @Date 2020/4/25 16:30
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ThesisScholarTest {

    @Resource
    private ThesisScholarService thesisScholarService;

    @Test
    public void deleteItemTest() {
        System.out.println(thesisScholarService.deleteItem("arar2017feature"));
    }
}
