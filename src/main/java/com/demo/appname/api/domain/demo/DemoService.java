package com.demo.appname.api.domain.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.appname.api.domain.demo.model.DemoVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public List<DemoVo> getUserList() {
        log.info("start Demo");
        List<DemoVo> userList = demoRepository.getUserList();
        log.debug("{}", userList);
        log.info("after getUserList");
        //String collect = listToStringBySeperator(userList, "|");
        log.info("after convert data");
        return userList;
    }

    @Transactional(readOnly = true)
    public String insertUser(String name) {
        demoRepository.insertUserByAnnotation(name);

        return "Creating User Info Done " + name;
    }

    @Transactional(readOnly = false)
    public void updateUserNameById(String userName, Long id) {
        demoRepository.updateUserNameById(userName, id);
    }

    @Transactional(readOnly = true)
    public List<DemoVo> getUserListByNames(String[] userNames) {
        List<DemoVo> userList = demoRepository.findByUserNamesListWithQuery(Stream.of(userNames).collect(Collectors.toList()));
        return userList;
    }

}
