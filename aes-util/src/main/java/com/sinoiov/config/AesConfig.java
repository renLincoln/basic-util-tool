package com.sinoiov.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author renhao
 * @Date 2021/5/18 14:57
 * @Description: 常量配置类
 */
@Component
@Data
public class AesConfig {
    @Value("aes.key")
    private String aesKsy;
}
