package com.github.neatlife.jframework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
@Validated
@Slf4j
public class K8sController {

    @GetMapping("/heartbeat")
    public Long heartbeat() {
        return System.currentTimeMillis();
    }

}
