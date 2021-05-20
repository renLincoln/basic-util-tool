package com.sinoiov.service;

public interface AesHandleService {
    // 加密
    int aesEnHandler() throws InterruptedException;
    // 还原
    int aesDnHandler() throws InterruptedException;
}
