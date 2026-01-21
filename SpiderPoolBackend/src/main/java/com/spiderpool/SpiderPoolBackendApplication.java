package com.spiderpool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spiderpool.mapper")
public class SpiderPoolBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderPoolBackendApplication.class, args);
    }

}
