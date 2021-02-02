package com.yshyerp.vehicle.service;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class TrustServiceTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TrustService trustService;

    @Test
    public void test() {


    }


}