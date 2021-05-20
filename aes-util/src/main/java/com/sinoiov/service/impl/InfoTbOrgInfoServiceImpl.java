package com.sinoiov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinoiov.dao.InfoTbOrgInfoDao;
import com.sinoiov.entity.InfoTbOrgInfo;
import com.sinoiov.service.InfoTbOrgInfoService;
import org.springframework.stereotype.Service;

/**
 * 组织详情表(InfoTbOrgInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-05-18 14:46:18
 */
@Service("infoTbOrgInfoService")
public class InfoTbOrgInfoServiceImpl extends ServiceImpl<InfoTbOrgInfoDao, InfoTbOrgInfo> implements InfoTbOrgInfoService {

}
