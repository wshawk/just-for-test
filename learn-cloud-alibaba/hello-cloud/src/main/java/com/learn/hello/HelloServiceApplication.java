package com.learn.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hawk
 * @package com.learn.hello
 * @desc TODO
 * @date 2020/12/18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HelloServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }
}
