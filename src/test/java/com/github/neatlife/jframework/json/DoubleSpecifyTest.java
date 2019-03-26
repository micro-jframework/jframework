package com.github.neatlife.jframework.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubleSpecifyTest {

    @Test
    public void test1() throws Exception {
        Product product = new Product(111231.5585, "小米手机");

        ObjectMapper objectMapper = new ObjectMapper();

        Product product1 = objectMapper.readValue(objectMapper.writeValueAsString(product), Product.class);

        Assert.assertEquals(111231.56D, product1.getPrice(), 0.0);
    }

    public static class Product {

        @DoubleSpecify
        private Double price;
        private String name;

        public Product() {
        }

        public Product(Double price, String name) {
            this.price = price;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

}