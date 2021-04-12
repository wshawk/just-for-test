package com.example.webserver;

/**
 * @author wsHawk
 * @Title: Server
 * @ProjectName just-for-test
 * @Description: TODO
 * @since 2021/4/11 14:13
 */
public class Server {
    public static void main(String[] args) {
        new WebServer().startServer(8000);
    }
}
