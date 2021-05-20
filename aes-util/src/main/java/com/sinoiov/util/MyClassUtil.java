package com.sinoiov.util;

import com.baomidou.mybatisplus.annotation.TableId;
import com.sinoiov.DTO.FieldDTO;
import com.sinoiov.anno.AesAnnoField;

import java.lang.reflect.Field;

/**
 * @Author renhao
 * @Date 2021/5/18 19:50
 * @Description: java反射相关工具类
 */
public class MyClassUtil {
    /**
     * @Author renhao
     * @Description: 使用java反射技术，将数据库实体类的字段按照主键与自定义注解进行划分，并封装至实体FieldDTO中
     * @Date: 2021/5/18 20:00
     * @Param: [targetClass]
     * @Return: com.sinoiov.DTO.FieldDTO
     */
    public static FieldDTO handleClassField(Class targetClass){
        Field[] fields = targetClass.getDeclaredFields();
        FieldDTO fieldDTO = new FieldDTO();
        for(Field field : fields){
            if(field.isAnnotationPresent(TableId.class)){
                fieldDTO.setKeyField(StringUtil.toUnderlineCase(field.getName()).toUpperCase());
            }else if(field.isAnnotationPresent(AesAnnoField.class)){
                fieldDTO.getAnnoField().add(StringUtil.toUnderlineCase(field.getName()).toUpperCase());
            }
        }
        return fieldDTO;
    }
}
