package com.github.neatlife.jframework.controller;

import com.github.neatlife.jframework.apiversion.ApiVersion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@CrossOrigin
@RestController
@RequestMapping("{v}/main")
public class MainController {

    /**
     * 方式一
     */
    @RequestMapping("main")
    @ApiVersion(1)
    public String main() {
        return "main1";
    }

    @RequestMapping("main")
    @ApiVersion(2)
    public String main2() {
        return "main2";
    }

    @RequestMapping("main")
    @ApiVersion(3)
    public String main3() {
        return "main3";
    }

    @RequestMapping("main")
    @ApiVersion(4)
    public String main4() {
        return "main";
    }

    /**
     * 方式二
     */
    @RequestMapping(value = "test", headers = "api-version=1")
    public String test1() {
        return "test1";
    }

    @RequestMapping(value = "test", headers = "api-version=2")
    public String test2() {
        return "test2";
    }

    @RequestMapping(value = "test", headers = "api-version=3")
    public String test3() {
        return "test3";
    }

}
