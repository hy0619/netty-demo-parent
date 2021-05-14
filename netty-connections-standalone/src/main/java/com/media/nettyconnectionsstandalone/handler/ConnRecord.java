package com.media.nettyconnectionsstandalone.handler;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 连接记录数
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class ConnRecord {
    private static volatile ConnRecord connRecord;
    /**
     * 连接数
     */
    private AtomicInteger connCount = new AtomicInteger(0);


    private ConnRecord(){

    }

    public static ConnRecord getInstance(){
        if(connRecord == null){
            synchronized (ConnRecord.class){
                if(connRecord == null){
                    connRecord = new ConnRecord();
                    connRecord.init(); //初始化 创建计数线程
                }
            }
        }
        return connRecord;
    }

    public void increase(){
        getInstance().connCount.incrementAndGet();
    }

    public void decrease(){
        getInstance().connCount.decrementAndGet();
    }

    private void init(){
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new DefaultThreadFactory("timer print thread"));
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("connections: " + connCount.get());
        }, 0, 2, TimeUnit.SECONDS);
    }




}
