package com.github.neatlife.jframework.fundation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@RestController
@RequestMapping("/")
@Slf4j
public class K8sController extends Controller {

    @GetMapping("/heartbeat")
    public Long heartbeat() {
        return System.currentTimeMillis();
    }

}
