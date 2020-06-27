package com.fuyk.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    /**
     * @RestController           相当于 @Controller + @ResponseBody
     * @GetMapping("/hello") 相当于 @RequestMapping(value = "/hello",method = RequestMethod.GET)
     * @return
     */
    @GetMapping("/hello")
    public String index() {
        return "Hello, SpringBoot!";
    }

}
