package com.sinoiov;

import com.sinoiov.util.AESUtil;

/**
 * @Author renhao
 * @Date 2021/5/19 17:51
 * @Description: aes的demo测试启动类
 */
public class DemoMain {
    public static void main(String[] args) {
        if(args == null || args.length < 2){
            System.out.println("参数异常，参数1为文本，参数2为aes类型");
        }
        String content = args[0];
        String aesFlag = args[1];
        System.out.println("获取到的文本为：" + content + ",aes类型：" + aesFlag);
        if ("e".equals(aesFlag)) {
            String str = AESUtil.getInstance().encryptStr(content);
            System.out.println("加密后的密文为：" + str);
        } else if ("e".equals(aesFlag)) {
            String str = AESUtil.getInstance().decryptStr(content);
            System.out.println("解密后的密文为：" + str);
        } else {
            System.out.println("there is one error.....");
        }
    }
}
