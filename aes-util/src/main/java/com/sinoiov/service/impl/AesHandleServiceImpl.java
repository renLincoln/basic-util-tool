package com.sinoiov.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinoiov.DTO.FieldDTO;
import com.sinoiov.config.AesConfig;
import com.sinoiov.entity.InfoSysSpOperator;
import com.sinoiov.entity.InfoTbEmployee;
import com.sinoiov.entity.InfoTbOrgInfo;
import com.sinoiov.entity.InfoTbSim;
import com.sinoiov.service.*;
import com.sinoiov.util.AESUtil;
import com.sinoiov.util.MyClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author renhao
 * @Date 2021/5/19 19:53
 * @Description: aes加密解密数据库字段service类
 *      通过排查得到需要进行字段脱敏加密处理的实体类有InfoSysSpOperator、InfoTbEmployee、InfoTbOrgInfo、InfoTbSim
 */
@Service
@Slf4j
public class AesHandleServiceImpl implements AesHandleService {
    @Autowired
    private InfoSysSpOperatorService infoSysSpOperatorService;
    @Autowired
    private InfoTbEmployeeService infoTbEmployeeService;
    @Autowired
    private InfoTbOrgInfoService infoTbOrgInfoService;
    @Autowired
    private InfoTbSimService infoTbSimService;
    @Autowired
    private AesConfig aesConfig;

    // 数据页大小
    private int pageSize = 100;

    @Override
    public int aesEnHandler() throws InterruptedException {
        aesHandlerEntrance(true);
        return 1;
    }

    @Override
    public int aesDnHandler() throws InterruptedException {
        aesHandlerEntrance(false);
        return 1;
    }

    /**
     * @Author renhao
     * @Description: aes对数据库加密解密入口
     * @Date: 2021/5/19 19:59
     * @Param: [encodeFlag(true为解密，false加密)]
     * @Return: void
     */
    private void aesHandlerEntrance(boolean encodeFlag) throws InterruptedException {
        // 用户表
        AesHandlerDetail(infoSysSpOperatorService, InfoSysSpOperator.class, encodeFlag);
        // 驾驶员表
        AesHandlerDetail(infoTbEmployeeService, InfoTbEmployee.class, encodeFlag);
        // 组织表
        AesHandlerDetail(infoTbOrgInfoService, InfoTbOrgInfo.class, encodeFlag);
        // SIM卡管理表
        AesHandlerDetail(infoTbSimService, InfoTbSim.class, encodeFlag);
    }
    /**
     * @Author renhao
     * @Description: aes加密详细处理
     * @Date: 2021/5/19 15:22
     * @Param: [iService, targetClass]
     * @Return: void
     */
    private void AesHandlerDetail(IService iService, Class targetClass,boolean encodeFlag) throws InterruptedException {
        // 获取需要实体类字段
        FieldDTO fieldDTO = MyClassUtil.handleClassField(targetClass);
        // 声明线程池
        int threadNum = Runtime.getRuntime().availableProcessors() + 1;
        ExecutorService execPool = Executors.newFixedThreadPool(threadNum);

        // 查询新总的个数
        int count = iService.count();
        if (count == 0) {
            log.error("未能能查询到表数据.......");
            return;
        }
        // 获取到最多的页码
        int maxPage = count / pageSize + 1;
        // 声明原子页码对象
        AtomicInteger atomicPage = new AtomicInteger(0);
        log.info("共计获取到{}条记录，分为{}批进行处理", count, maxPage);
        // 声明线程执行方法
        Runnable task = () -> {
            // 日志收集处理
            // 初始化相关数据
            int currentPage = 0;
            int queryCount = 0;
            int successCount = 0;
//            List<String> idList = new ArrayList<>();
            try {
                if (atomicPage.get() > maxPage) {
                    log.warn("分页查询已经超出范围，不再处理........");
                    return;
                }
                // 分页码原子累计
                currentPage = atomicPage.incrementAndGet();
                // 从数据库中获取数据信息
                QueryWrapper qw = new QueryWrapper();
                qw.select(fieldDTO.getKeyField() + "," +
                        fieldDTO.getAnnoField().stream().collect(Collectors.joining(",")));
                Page objectPage = new Page<>(currentPage, pageSize);
                IPage page = iService.page(objectPage, qw);
                // 获取查询到的数据集合
                List records = page.getRecords();
//                records.forEach(e -> idList.add(e.getOpId()));
                if (CollectionUtils.isEmpty(records)) {
                    log.warn("分页查询未能查询到数据，页码：{}，分页大小{}", currentPage, pageSize);
                    return;
                }
                queryCount = records.size();
                // 遍历查询得到的列表，并将以及更改的表记录进行记录
                List haveAesRecords = new ArrayList();
                for (Object object : records) {
                    Boolean haveAesFlag = AESUtil.getInstance().aesConvert(object, true, aesConfig.getAesKsy(), encodeFlag);
                    if (haveAesFlag) {
                        haveAesRecords.add(object);
                    }
                }
                // 批量更新数据
                if (!CollectionUtils.isEmpty(haveAesRecords)) {
                    iService.updateBatchById(haveAesRecords);
                    successCount += haveAesRecords.size();
                }
            } catch (Exception ex) {
                log.error("##字段加密处理异常，当前处理页码:{},", currentPage, ex);
            } finally {
                log.info("##当前处理页码:{},本次查询到的数据量：{}，成功加密处理数据量：{}", currentPage, queryCount, successCount);
            }
        };
        // 提交任务，线程执行
        for (int i = 0; i < maxPage; i++) {
            execPool.submit(task);
            if (atomicPage.get() > maxPage + 1)
                break;
        }
        execPool.shutdown();
        while (true) {
            if (execPool.isTerminated()) {
                System.out.println("所有的子线程都结束了！");
                log.info("main thread is end......");
                break;
            }
            Thread.sleep(1000);
        }
    }
}
