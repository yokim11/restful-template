package com.demo.appname.api.controller.v1.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.appname.api.controller.v1.demo.mapper.DemoMapper;
import com.demo.appname.api.controller.v1.demo.model.DemoDto;
import com.demo.appname.api.domain.demo.DemoService;
import com.demo.appname.api.domain.demo.model.Demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RestController
@RequestMapping(value = "/api/demos")
public class DemoController {
    
    @Autowired
    private DemoService demoService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity<List<DemoDto>> demos() {
        List<Demo> userList = demoService.getUserList();
        return ResponseEntity.ok(DemoMapper.getMapper().demoListToDemoDtoList(userList));
    }

    @PostMapping(value = "/users/{name}")
    public String insertUser(@PathVariable("name") String name) {
        log.info("Insert User {}", name);
        String result = demoService.insertUser(name);
        log.info("After Insert User {}", name);
        return result;
    }

    @GetMapping(value = "/users/names")
    public <T> ResponseEntity<List<DemoDto>> findByUserNames(@RequestParam(value = "names") String[] names) {
    	List<Demo> userList = demoService.getUserListByNames(names);
    	return ResponseEntity.ok(DemoMapper.getMapper().demoListToDemoDtoList(userList));
    }
}
