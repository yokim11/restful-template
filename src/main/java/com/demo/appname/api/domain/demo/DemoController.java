package com.demo.appname.api.domain.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.appname.api.common.helper.ModelMapperHelper;
import com.demo.appname.api.domain.demo.model.DemoVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RestController
@RequestMapping(value = "/api/demos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {
    
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public <T> ResponseEntity<List<DemoVo>> demos() {
        List<DemoVo> userList = demoService.getUserList();
        return ResponseEntity.ok(ModelMapperHelper.mapList(userList, DemoVo.class));
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.POST)
    public String insertUser(@PathVariable("name") String name) {
        log.info("Insert User {}", name);
        String result = demoService.insertUser(name);
        log.info("After Insert User {}", name);

        return result;
    }

    @RequestMapping(value = "/users/{userNames}", method = RequestMethod.GET)
    public <T> ResponseEntity<List<DemoVo>> findByUserNames(@PathVariable String[] userNames) {
    	List<DemoVo> userList = demoService.getUserListByNames(userNames);
    	return ResponseEntity.ok(ModelMapperHelper.mapList(userList, DemoVo.class));
    }
}
