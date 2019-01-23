package io.renren.modules.CTG.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianyi
 * @date 2018-11-24 22:13
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public String sayHello(){
        return "Welcome! Use API success! ";
    }

}
