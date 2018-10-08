package com.example.postgretest;

import com.example.postgretest.Model.Hello;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloTest {
    RestTemplate restTemplate;

    @Before
    public void setup(){
        restTemplate = new RestTemplate();
    }


    @Test
    public void post(){
        Hello hello = new Hello();
        hello.setName("jaeyun");
        hello.setText("hi");
        hello.setTimecreated(Timestamp.from(new Date().toInstant()));
        restTemplate.postForObject("/hello", hello, Hello.class);
    }
}
