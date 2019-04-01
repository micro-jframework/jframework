package com.github.neatlife.jframework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/1.png"))) {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());
        }
    }

    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(String param) throws IOException {
        File file = new File("src/main/resources/1.png");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=1.png");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
