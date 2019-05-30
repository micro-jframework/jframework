package com.github.neatlife.jframework.test.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neatlife.jframework.fundation.json.BigDecimalSpecify;
import com.github.neatlife.jframework.test.JframeworkApplicationTests;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalSpecifyTest extends JframeworkApplicationTests {

    @Test
    public void test1() throws Exception {
        Product product = new Product(new BigDecimal("111231.5585"), "小米手机");

        ObjectMapper objectMapper = new ObjectMapper();

        Product product1 = objectMapper.readValue(objectMapper.writeValueAsString(product), Product.class);

        Assert.assertEquals(new BigDecimal("111231.559"), product1.getPrice());
    }

    public static class Product {

        @BigDecimalSpecify(3)
        private BigDecimal price;
        private String name;

        public Product() {
        }

        public Product(BigDecimal price, String name) {
            this.price = price;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

}