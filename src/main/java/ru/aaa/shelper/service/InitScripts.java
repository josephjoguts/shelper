package ru.aaa.shelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InitScripts implements CommandLineRunner {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {

    }
}
