package com.example.postgretest.Controller;

import com.example.postgretest.Repository.HelloRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SecurityController {

    final HelloRepositoy helloRepositoy;

    @Autowired
    public SecurityController(HelloRepositoy helloRepositoy) {
        this.helloRepositoy = helloRepositoy;
    }

}
