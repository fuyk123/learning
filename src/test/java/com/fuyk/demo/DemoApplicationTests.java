package com.fuyk.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        //assertEquals("Hello,World!",new HelloWorldController().sayHello());
    }

    @Test
    public void jacksonTest() throws JsonProcessingException {
        Map map = new HashMap<>();
        map.put("name", "fuyk");
        //new 一个Jackson 对象
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化对象
        String jsonString = objectMapper.writeValueAsString(map);
        System.out.println(jsonString);
        //反序列化成对象
        Map map1 = objectMapper.readValue(jsonString,Map.class);
        System.out.println(map1.get("name"));


    }
}
