package com.springdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdata.domain.Product;
import com.springdata.repository.ProductRepository;

@RestController
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "products")
    public List<Product> getProducts() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT", new BeanPropertyRowMapper(Product.class));
    }

}
