本次需要对下列表中：
手机号、身份证号、邮箱、联系方式、地址、
邮箱、传真号、从业资格证号
字段进行aes加密
SIM卡管理： TB_SIM
用户：    SYS_SP_OPERATOR
组织：    TB_ORG_INFO
驾驶员：  TB_EMPLOYEE
车辆表：   TB_VEHICLE
终端管理：  TB_TERMINAL
驾驶员IC卡管理：TB_IC_CARD


通过排查得到需要进行字段脱敏加密处理的实体类有InfoSysSpOperator、InfoTbEmployee、InfoTbOrgInfo、InfoTbSim
1、数据库操作，分别拷贝赋值如下表：
create table INFO_SYS_SP_OPERATOR as select * from SYS_SP_OPERATOR;
create table INFO_TB_EMPLOYEE as select * from TB_EMPLOYEE;
create table INFO_TB_ORG_INFO as select * from TB_ORG_INFO;
create table INFO_TB_SIM as select * from TB_SIM;

2、再执行如下sql脚本，对拷贝复制后的表字段做字段长度扩展（加密后的字段叫加密前的字段要长，若不对字段长度做扩增，数据更新入库时会出现mysql异常）
-- 用户表字段长度扩展 INFO_SYS_SP_OPERATOR
alter table INFO_SYS_SP_OPERATOR modify(OP_ADDRESS varchar(600));
alter table INFO_SYS_SP_OPERATOR modify(OP_MOBILE varchar(60));
alter table INFO_SYS_SP_OPERATOR modify(OP_PHONE varchar(60));
alter table INFO_SYS_SP_OPERATOR modify(OP_FAX varchar(60));
alter table INFO_SYS_SP_OPERATOR modify(OP_EMAIL varchar(100));
alter table INFO_SYS_SP_OPERATOR modify(OP_IDENTITY_ID varchar(100));

-- 驾驶员表字段长度扩展 INFO_TB_EMPLOYEE
alter table INFO_TB_EMPLOYEE modify(VEHICLEPHONE varchar(60));
alter table INFO_TB_EMPLOYEE modify(FIXEDPHONE varchar(60));
alter table INFO_TB_EMPLOYEE modify(MOBILEPHONE varchar(60));
alter table INFO_TB_EMPLOYEE modify(EMGC_PHONE varchar(60));
alter table INFO_TB_EMPLOYEE modify(FAX varchar(60));
alter table INFO_TB_EMPLOYEE modify(EMAIL varchar(100));
alter table INFO_TB_EMPLOYEE modify(ADDRESS varchar(200));
alter table INFO_TB_EMPLOYEE modify(CARD_ID varchar(100));
alter table INFO_TB_EMPLOYEE modify(BUSSINESS_ID varchar(100));

-- 组织表字段长度扩展 INFO_TB_ORG_INFO
alter table INFO_TB_ORG_INFO modify(ORG_ADDRESS varchar(160));
alter table INFO_TB_ORG_INFO modify(ORG_CFAX varchar(60));
alter table INFO_TB_ORG_INFO modify(ORG_CMAIL varchar(100));
alter table INFO_TB_ORG_INFO modify(ORG_CNO varchar(60));
alter table INFO_TB_ORG_INFO modify(ORG_CPHONE varchar(100));

-- SIM卡管理表字段长度扩展 INFO_TB_SIM
alter table INFO_TB_SIM modify(COMMADDR varchar(200));

3、当数据库处理完成后，使用压缩工具中打开aes-util-1.0-SNAPSHOT.jar架包
更改aes-util-1.0-SNAPSHOT.jar\BOOT-INF\classes\application.yml配置文件中的数据库配置信息（当前配置信息为测试服务器配置）

4、启动jar包：jar -jar aes-util-1.0-SNAPSHOT.jar

5、调用程序接口完成特定表字段的AES加密，接口POST: http://127.0.0.1:8877/aes/enHandler
