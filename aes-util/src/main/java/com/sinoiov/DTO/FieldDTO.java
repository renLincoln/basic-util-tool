package com.sinoiov.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author renhao
 * @Date 2021/5/18 19:53
 * @Description: 字段DTO实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
    /**
     * 主键字段
     */
    private String keyField;
    /**
     * 注解字段集合
     */
    private List<String> annoField = new ArrayList<>();
}
