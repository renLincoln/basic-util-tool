package com.sinoiov.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sinoiov.DTO.FieldDTO;
import com.sinoiov.MainApplication;
import com.sinoiov.config.AesConfig;
import com.sinoiov.entity.InfoSysSpOperator;
import com.sinoiov.entity.InfoTbEmployee;
import com.sinoiov.entity.InfoTbOrgInfo;
import com.sinoiov.entity.InfoTbSim;
import com.sinoiov.util.AESUtil;
import com.sinoiov.util.SelfAesUtil;
import com.sinoiov.util.MyClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class AesServiceTest {
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

    // 通过排查得到需要进行字段脱敏加密处理的实体类有InfoSysSpOperator、InfoTbEmployee、InfoTbOrgInfo、InfoTbSim
    // 考虑到部分表数据量较多，处理效率会比较慢，为了加快数据处理效率，考虑如下策略
    // 1、首先获取对应表所有的主键信息，并存放到set集合中
    // 2、加入线程池，使用多线程方式批量进行数据脱敏处理，每个线程每次从set集合中获取固定大小的主键集合（表一般为行级锁），按照所需要脱敏的字段进行数据查询
    // 3、获取到集合后，对集合进行脱敏加密，再批量更新
    // 4、需要多线程竞争同一个set集合问题，在获取set集合时，需要进行加锁
    // 5、需要考虑日志问题，记录脱敏失败数据，方便后续对异常未脱敏数据进行下一次脱敏
    @Test
//    @Transactional
    public void AesEnHandlerNew() throws IllegalAccessException, InterruptedException {
        // 用户表加密
        AesHandlerDetail(infoSysSpOperatorService, InfoSysSpOperator.class);
        // 驾驶员表加密
        AesHandlerDetail(infoTbEmployeeService, InfoTbEmployee.class);
        // 组织表加密
        AesHandlerDetail(infoTbOrgInfoService, InfoTbOrgInfo.class);
        // SIM卡管理表加密
        AesHandlerDetail(infoTbSimService, InfoTbSim.class);
    }

    @Test
    public void AesDnHandlerTest(){
        Page<InfoTbSim> objectPage = new Page<>(0, pageSize);
        IPage<InfoTbSim> page = infoTbSimService.page(objectPage);
        List<InfoTbSim> records = page.getRecords();
        records.forEach(e-> {
            try {
                AESUtil.getInstance().aesConvert(e,false,aesConfig.getAesKsy(),false);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });
        System.out.println("1");
    }

    /**
     * @Author renhao
     * @Description: aes加密详细处理
     * @Date: 2021/5/19 15:22
     * @Param: [iService, targetClass]
     * @Return: void
     */
    private void AesHandlerDetail(IService iService, Class targetClass) throws InterruptedException {
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
                    Boolean haveAesFlag = AESUtil.getInstance().aesConvert(object, true, aesConfig.getAesKsy(), true);
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
