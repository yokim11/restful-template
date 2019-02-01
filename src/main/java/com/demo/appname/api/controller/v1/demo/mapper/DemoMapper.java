package com.demo.appname.api.controller.v1.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.demo.appname.api.controller.v1.demo.model.DemoDto;
import com.demo.appname.api.domain.demo.model.Demo;

@Mapper
public interface DemoMapper {
	
	public static DemoMapper getMapper() {
		return Mappers.getMapper(DemoMapper.class);
	}
	
//	@Mapping(source = "name", target = "name")
	DemoDto demoToDemoDto(Demo demo);

	List<DemoDto> demoListToDemoDtoList(List<Demo> demoList);
}
