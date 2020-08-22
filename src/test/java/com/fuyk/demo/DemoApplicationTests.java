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
        Employee employee = new Employee();
        employee.setAge(24);
        //new 一个Jackson 对象
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化对象,write
        String jsonString1 = objectMapper.writeValueAsString(map);
        String jsonString2 = objectMapper.writeValueAsString(employee);
        System.out.println(jsonString2);
        //反序列化成对象,read
        Map map2 = objectMapper.readValue(jsonString1,Map.class);
        Employee employee2 = objectMapper.readValue(jsonString2,Employee.class);
        System.out.println(employee.getAge());


    }
}
