package com.demo.appname.api.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags="홈")
@RestController
@RequestMapping(value = "/api/v1", method = RequestMethod.GET)
public class HomeController {

  @Value("${spring.application.name}")
  private String server;
	
	@RequestMapping("/")
    public @ResponseBody String home() {
		log.info("home controller started.");
        return ("This is " + server + " project.");
    }

}
