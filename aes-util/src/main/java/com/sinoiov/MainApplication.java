package com.sinoiov;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author renhao
 * @Date 2021/5/18 11:12
 * @Description: 启动类主程序
 */
@SpringBootApplication
@MapperScan(basePackages = "com.sinoiov.dao")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
