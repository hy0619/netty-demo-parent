package com.media;

/**
 * @Description TODO
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class ServerMain {


    private static final int BEGIN_PORT = 8000;
    private static final int N_PORT = 100;

    public static void main(String[] args) {
        new Server().start(BEGIN_PORT, N_PORT);
    }
}
