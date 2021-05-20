package com.sinoiov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinoiov.dao.InfoTbEmployeeDao;
import com.sinoiov.entity.InfoTbEmployee;
import com.sinoiov.service.InfoTbEmployeeService;
import org.springframework.stereotype.Service;

/**
 * 驾驶员表(InfoTbEmployee)表服务实现类
 *
 * @author makejava
 * @since 2021-05-18 14:46:17
 */
@Service("infoTbEmployeeService")
public class InfoTbEmployeeServiceImpl extends ServiceImpl<InfoTbEmployeeDao, InfoTbEmployee> implements InfoTbEmployeeService {

}
