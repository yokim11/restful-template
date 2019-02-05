package com.demo.appname.api.controller.v1;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.appname.api.common.exception.DataNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RestController
@RequestMapping(value = "/api/exception")
public class ExceptionController {
    
    @GetMapping(value = "/nodata", produces = MediaType.APPLICATION_JSON_VALUE)
    public String demos() {
    	throw new DataNotFoundException("데이터 없음");
    }

}
