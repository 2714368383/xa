package com.base.net.xa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.base.net.xa.mapper.*")
@SpringBootApplication
public class XaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XaApplication.class, args);
    }

}
