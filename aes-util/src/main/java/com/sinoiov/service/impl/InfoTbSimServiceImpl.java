package com.sinoiov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinoiov.dao.InfoTbSimDao;
import com.sinoiov.entity.InfoTbSim;
import com.sinoiov.service.InfoTbSimService;
import org.springframework.stereotype.Service;

/**
 * SIM????(InfoTbSim)表服务实现类
 *
 * @author makejava
 * @since 2021-05-18 14:46:18
 */
@Service("infoTbSimService")
public class InfoTbSimServiceImpl extends ServiceImpl<InfoTbSimDao, InfoTbSim> implements InfoTbSimService {

}
