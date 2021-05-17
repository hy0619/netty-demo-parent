package com.media;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
@Slf4j
public class ServerMain {


    private static final int PORT = 8000;

    public static void main(String[] args) {
        new Server().start(PORT);
    }
}
