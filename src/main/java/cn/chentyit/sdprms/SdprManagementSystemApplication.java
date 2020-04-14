package cn.chentyit.sdprms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.chentyit.sdprms.dao"})
public class SdprManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdprManagementSystemApplication.class, args);
    }

}
