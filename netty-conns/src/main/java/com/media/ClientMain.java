package com.media;

/**
 * @Description TODO
 * @Author Hero
 * @Date 2021/5/14
 * @Version 1.0.0
 */
public class ClientMain {
    private static final int BEGIN_PORT = 8000;
    private static final int N_PORT = 100;

    public static void main(String[] args) {
        new Client().start(BEGIN_PORT);
    }
}
