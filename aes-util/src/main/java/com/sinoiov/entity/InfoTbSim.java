package com.sinoiov.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sinoiov.anno.AesAnnoField;

/**
 * SIM????(InfoTbSim)表实体类
 *
 * @author makejava
 * @since 2021-05-18 14:46:18
 */
@SuppressWarnings("serial")
public class InfoTbSim extends Model<InfoTbSim> {
    //SIM卡ID,由序列SEQ_SIM_ID生成
    @TableId
    private String sid;
    //所属实体ID
    private String entId;
    //通讯地址（手机号码）
    @AesAnnoField
    private String commaddr;
    //服务单元密码，电话查车时使用
    private String password;
    //电子ICCID
    private String iccidElectron;
    //电信运营商
    private String businessId;
    //套餐id
    private String comboId;
    //业务开通日期
    private String svcStart;
    //业务关闭日期
    private String svcStop;
    //备注
    private String sudesc;
    //sim卡所属地区（省）
    private String province;
    //创建人id
    private String createBy;
    //创建时间
    private String createTime;
    //修改人id
    private String updateBy;
    //修改时间
    private String updateTime;
    //有效标记 1:有效 0:无效 2:吊销 默认为1
    private String enableFlag;
    //Sim卡状态  1：空闲 2：已绑定  3：吊销
    private String simState;
    //印刷ICCID
    private String iccidPrint;
    //IMSI
    private String imsi;
    //APN接入点名称
    private String apn;
    //PIN
    private String pin;
    //PUK
    private String puk;
    //sim卡所属地区（市）
    private String city;
    //真实SIM卡号
    private String realcommaddr;
    //交付状态(0:交付中;1:未交付;2:已交付;3:交付未通过)
    private String deliveryStatus;
    //开卡日期
    private String openTime;
    //失效日期
    private String expireTime;
    //最近缴费日期
    private String lastPayTime;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getCommaddr() {
        return commaddr;
    }

    public void setCommaddr(String commaddr) {
        this.commaddr = commaddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIccidElectron() {
        return iccidElectron;
    }

    public void setIccidElectron(String iccidElectron) {
        this.iccidElectron = iccidElectron;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getSvcStart() {
        return svcStart;
    }

    public void setSvcStart(String svcStart) {
        this.svcStart = svcStart;
    }

    public String getSvcStop() {
        return svcStop;
    }

    public void setSvcStop(String svcStop) {
        this.svcStop = svcStop;
    }

    public String getSudesc() {
        return sudesc;
    }

    public void setSudesc(String sudesc) {
        this.sudesc = sudesc;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getSimState() {
        return simState;
    }

    public void setSimState(String simState) {
        this.simState = simState;
    }

    public String getIccidPrint() {
        return iccidPrint;
    }

    public void setIccidPrint(String iccidPrint) {
        this.iccidPrint = iccidPrint;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPuk() {
        return puk;
    }

    public void setPuk(String puk) {
        this.puk = puk;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRealcommaddr() {
        return realcommaddr;
    }

    public void setRealcommaddr(String realcommaddr) {
        this.realcommaddr = realcommaddr;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(String lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

}
