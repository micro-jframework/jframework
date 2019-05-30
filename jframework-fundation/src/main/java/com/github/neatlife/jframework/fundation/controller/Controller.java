package com.github.neatlife.jframework.fundation.controller;

import com.github.neatlife.jframework.fundation.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author suxiaolin
 * @date 2019-03-07 12:39
 */
@CrossOrigin(origins = "*")
@Slf4j
public class Controller {

    public <E> Response<E> success(E body) {
        return Response.success(body);
    }

}
