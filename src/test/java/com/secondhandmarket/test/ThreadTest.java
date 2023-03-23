package com.secondhandmarket.test;

import cn.hutool.core.io.file.FileWriter;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {
    @Test
    public void method(){
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(()->{
                FileWriter fileWriter = new FileWriter("test" + Thread.currentThread().getName() + ".txt");
                for (int j=100;j>0;j--){
                    fileWriter.append("test"+j+"\n");
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主进程结束");
    }
    @Test
    public void method1(){
        FileWriter fileWriter = new FileWriter("test" + Thread.currentThread().getName() + ".txt");
        String path = fileWriter.getFile().getPath();
        for (int j=100;j>0;j--){
            fileWriter.append("test"+j+"\n");
        }
    }
}


