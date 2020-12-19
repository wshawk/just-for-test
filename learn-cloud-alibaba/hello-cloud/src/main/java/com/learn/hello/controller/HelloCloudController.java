package com.learn.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author A80068
 * @package com.learn.hello.controller
 * @desc TODO
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/hello")
public class HelloCloudController {

    @GetMapping("/cloud")
    public String helloCloud(){
        return "hello cloud";
    }
}
