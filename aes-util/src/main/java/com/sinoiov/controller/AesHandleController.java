package com.sinoiov.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinoiov.entity.InfoSysSpOperator;
import com.sinoiov.service.AesHandleService;
import com.sinoiov.service.InfoSysSpOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * AES控制层
 *
 * @author rh
 * @since 2021-05-18 14:46:17
 */
@RestController
@RequestMapping("aes")
public class AesHandleController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private AesHandleService aesHandleService;

    /**
     * @Author renhao
     * @Description: 数据库加密操作
     * @Date: 2021/5/19 20:04
     * @Param: []
     * @Return: com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("enHandler")
    public R aesEnHandler() throws InterruptedException {
        return success(aesHandleService.aesEnHandler());
    }

    /**
     * @Author renhao
     * @Description: 数据库解密操作
     * @Date: 2021/5/19 20:04
     * @Param: []
     * @Return: com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("dnHandler")
    public R aesDnHandler() throws InterruptedException {
        return success(aesHandleService.aesDnHandler());
    }

}
