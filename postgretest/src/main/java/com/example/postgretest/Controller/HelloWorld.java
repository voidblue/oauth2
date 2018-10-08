package com.example.postgretest.Controller;

import com.example.postgretest.Model.Hello;
import com.example.postgretest.Repository.HelloRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("hello")
public class HelloWorld {

    private final HelloRepositoy helloRepositoy;

    @Autowired
    public HelloWorld(HelloRepositoy helloRepositoy) {
        this.helloRepositoy = helloRepositoy;
    }

    @GetMapping("/{id}")
    public Hello helloworld(@PathVariable Integer id){
        return helloRepositoy.findById(id).orElse(new Hello());
    }

    @PostMapping
    public Hello save(@RequestBody Hello hello){
        hello.setTimecreated(Timestamp.from(new Date().toInstant()));
        return helloRepositoy.save(hello);
    }
}
