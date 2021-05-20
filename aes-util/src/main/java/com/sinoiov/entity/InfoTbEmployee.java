package com.sinoiov.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sinoiov.anno.AesAnnoField;

/**
 * 驾驶员表(InfoTbEmployee)表实体类
 *
 * @author makejava
 * @since 2021-05-18 14:46:17
 */
@SuppressWarnings("serial")
public class InfoTbEmployee extends Model<InfoTbEmployee> {
    //人员ID 由序列SEQ_STAFF_ID生成
    @TableId
    private String staffId;
    //人员编号
    private String staffCode;
    //人员名称
    private String staffName;
    //人员类别 0 驾驶员 1售票员等
    private String staffType;
    //驾驶证号
    private String driverNo;
    //驾驶证有效日期utc(s)
    private String driverValdate;
    //驾驶证年审日期utc(s)
    private String driverChdate;
    //从业开始时间
    private String incareerDate;
    //查询密码
    private String cqpasswd;
    //客户管理密码
    private String cmpasswd;
    //车载电话号码
    @AesAnnoField
    private String vehiclephone;
    //固定电话号码
    @AesAnnoField
    private String fixedphone;
    //移动电话号码
    @AesAnnoField
    private String mobilephone;
    //性别1-男性2-女性标准5.9.1
    private String sex;
    //出生日期utc(s)
    private String birthday;
    //紧急情况联系人
    private String emgcContactor;
    //紧急情况联系电话
    @AesAnnoField
    private String emgcPhone;
    //传真号码
    @AesAnnoField
    private String fax;
    //电子邮件
    @AesAnnoField
    private String email;
    //住址
    @AesAnnoField
    private String address;
    //邮编
    private String postcode;
    //证件类型
    private String cardType;
    //证件号码 (身份证号)
    @AesAnnoField
    private String cardId;
    //创建人
    private String createBy;
    //创建时间
    private String createTime;
    //修改人
    private String updateBy;
    //修改时间
    private String updateTime;
    //有效标记 1:有效 0:无效 默认为1
    private String enableFlag;
    //人员状态：1：空闲  2：已绑定   3：吊销
    private String staffState;
    //驾驶证核发机关
    private String drivercardDep;
    //从业资格证号
    @AesAnnoField
    private String bussinessId;
    //所属实体id
    private String entId;
    //驾驶证档案号
    private String archiveno;
    //驾驶员IC卡号
    private String driverIccard;
    //驾驶员照片
    private String employeeImg;
    //准驾证号
    private String drivingPermits;
    //准驾车型 0-A1  1-B1  2-C1  3-A2  4-A3  5-B2  6-C2  7-C3  8-C4
    private String quasiDrivingType;
    //驾驶证年审提醒天数
    private String driverTipDays;
    //从业资格证有效期至(UTC长时间)
    private String bussinessValdate;
    //从业资格证年审提醒天数
    private String bussinessTipDatys;
    //从业资格证年审日期utc(s)
    private String bussinessChdate;
    //图片大小
    private String imgSize;
    //图片名称
    private String imgName;
    //图片类型
    private String imgType;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }

    public String getDriverValdate() {
        return driverValdate;
    }

    public void setDriverValdate(String driverValdate) {
        this.driverValdate = driverValdate;
    }

    public String getDriverChdate() {
        return driverChdate;
    }

    public void setDriverChdate(String driverChdate) {
        this.driverChdate = driverChdate;
    }

    public String getIncareerDate() {
        return incareerDate;
    }

    public void setIncareerDate(String incareerDate) {
        this.incareerDate = incareerDate;
    }

    public String getCqpasswd() {
        return cqpasswd;
    }

    public void setCqpasswd(String cqpasswd) {
        this.cqpasswd = cqpasswd;
    }

    public String getCmpasswd() {
        return cmpasswd;
    }

    public void setCmpasswd(String cmpasswd) {
        this.cmpasswd = cmpasswd;
    }

    public String getVehiclephone() {
        return vehiclephone;
    }

    public void setVehiclephone(String vehiclephone) {
        this.vehiclephone = vehiclephone;
    }

    public String getFixedphone() {
        return fixedphone;
    }

    public void setFixedphone(String fixedphone) {
        this.fixedphone = fixedphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmgcContactor() {
        return emgcContactor;
    }

    public void setEmgcContactor(String emgcContactor) {
        this.emgcContactor = emgcContactor;
    }

    public String getEmgcPhone() {
        return emgcPhone;
    }

    public void setEmgcPhone(String emgcPhone) {
        this.emgcPhone = emgcPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getStaffState() {
        return staffState;
    }

    public void setStaffState(String staffState) {
        this.staffState = staffState;
    }

    public String getDrivercardDep() {
        return drivercardDep;
    }

    public void setDrivercardDep(String drivercardDep) {
        this.drivercardDep = drivercardDep;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getArchiveno() {
        return archiveno;
    }

    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno;
    }

    public String getDriverIccard() {
        return driverIccard;
    }

    public void setDriverIccard(String driverIccard) {
        this.driverIccard = driverIccard;
    }

    public String getEmployeeImg() {
        return employeeImg;
    }

    public void setEmployeeImg(String employeeImg) {
        this.employeeImg = employeeImg;
    }

    public String getDrivingPermits() {
        return drivingPermits;
    }

    public void setDrivingPermits(String drivingPermits) {
        this.drivingPermits = drivingPermits;
    }

    public String getQuasiDrivingType() {
        return quasiDrivingType;
    }

    public void setQuasiDrivingType(String quasiDrivingType) {
        this.quasiDrivingType = quasiDrivingType;
    }

    public String getDriverTipDays() {
        return driverTipDays;
    }

    public void setDriverTipDays(String driverTipDays) {
        this.driverTipDays = driverTipDays;
    }

    public String getBussinessValdate() {
        return bussinessValdate;
    }

    public void setBussinessValdate(String bussinessValdate) {
        this.bussinessValdate = bussinessValdate;
    }

    public String getBussinessTipDatys() {
        return bussinessTipDatys;
    }

    public void setBussinessTipDatys(String bussinessTipDatys) {
        this.bussinessTipDatys = bussinessTipDatys;
    }

    public String getBussinessChdate() {
        return bussinessChdate;
    }

    public void setBussinessChdate(String bussinessChdate) {
        this.bussinessChdate = bussinessChdate;
    }

    public String getImgSize() {
        return imgSize;
    }

    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

}
