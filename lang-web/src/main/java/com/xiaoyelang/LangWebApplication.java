package com.xiaoyelang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.xiaoyelang.mapper.*")
public class LangWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LangWebApplication.class, args);
    }

}
