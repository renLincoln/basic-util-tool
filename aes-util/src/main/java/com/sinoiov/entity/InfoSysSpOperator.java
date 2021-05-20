package com.sinoiov.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sinoiov.anno.AesAnnoField;
import lombok.Data;

/**
 * ??????
 * ????????????????????
 * ????????????????????????ENABLE_FLAG????0
 * ??????????????????????????????????????????????????????????????????????(InfoSysSpOperator)表实体类
 *
 * @author makejava
 * @since 2021-05-18 14:46:05
 */
// 加密字段 手机号、身份证号、邮箱、联系方式、地址、邮箱、传真号、从业资格证号
@SuppressWarnings("serial")
@Data
public class InfoSysSpOperator extends Model<InfoSysSpOperator> {
    //帐号id，使用序列SEQ_OP_ID
    @TableId
    private String opId;
    //所属实体ID
    private String entId;
    //登录名
    private String opLoginname;
    //密码(Base64+MD5)
    private String opPass;
    //固定鉴权码：后期扩展登陆用户和硬件绑定功能，使用uKey还是绑定电脑硬件编号
    private String opAuthcode;
    //姓名
    private String opName;
    //帐号类型 0: 管理账号1:应用系统账号
    private String opSuper;
    //性别 (0:男 1:女)
    private String opSex;
    //出生日期
    private String opBirth;
    //所属国家ID
    private String opCountry;
    //所属省ID
    private String opProvince;
    //所属市ID
    private String opCity;
    //地址
    @AesAnnoField
    private String opAddress;
    //手机
    @AesAnnoField
    private String opMobile;
    //电话
    @AesAnnoField
    private String opPhone;
    //传真
    @AesAnnoField
    private String opFax;
    //邮件
    @AesAnnoField
    private String opEmail;
    //身份证号
    @AesAnnoField
    private String opIdentityId;
    //职位
    private String opDuty;
    //工号
    private String opWorkid;
    //帐号开通日期
    private String opStartutc;
    //用户类型 0平台管理员 1企业用户 2代理商用户 3车厂用户 4车主用户
    private String opType;
    //备注
    private String opMem;
    //创建人id
    private String createBy;
    //创建时间
    private String createTime;
    //修改人id
    private String updateBy;
    //修改时间
    private String updateTime;
    //有效标记 1:有效 0:无效 默认为1
    private String enableFlag;
    //邮编
    private String opZip;
    //帐号状态，禁用帐号不能登录系统 1:启用 0: 禁用
    private String opStatus;
    //是否会员  1:是         0:不是
    private String isMember;
    //IMSI号码
    private String imsi;
    //会员照片
    private String photo;
    //账户最大有效期
    private String opEndutc;
    //换肤皮肤样式
    private String skinStyle;
    //重置密码时间
    private String resetPasswordTime;
    //1：多组织，其他为否
    private String isManyOrg;
    //用户岗位 0、默认；1、安全科长；2、安全经理
    private String opStation;
    //是否限制:0、否，1、是
    private String isTrackLimit;
    //限制开始时间
    private String limitStarttime;
    //限制结束时间
    private String limitEndtime;

}
