package com.demo.appname.api.controller.v1.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DemoDto {

	@ApiModelProperty(value = "id", notes = "id", example = "1")
    private int id;
	
	@ApiModelProperty(value = "이름", notes = "이", example = "홍길동")
    private String name;
}
