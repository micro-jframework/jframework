package com.github.neatlife.jframework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController extends Controller {

    @GetMapping("/send-error-email")
    public String sendErrorEmail() {
        log.error("这个是一个错误");
        return "hello world.";
    }

}
