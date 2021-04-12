package com.example.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wsHawk
 * @Title: WebServer
 * @ProjectName just-for-test
 * @Description: TODO
 * @since 2021/4/11 14:13
 */
public class WebServer {
        public void startServer(int port){
            try {
                @SuppressWarnings("resource")
                ServerSocket serverSocket = new ServerSocket(port);
                while(true){
                    Socket socket = serverSocket.accept();
                    new HttpServer(socket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
