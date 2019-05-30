package com.github.neatlife.jframework.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendEmailTest extends JframeworkApplicationTests {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void send() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jframework");
        message.setTo("dear.lin@live.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }
}