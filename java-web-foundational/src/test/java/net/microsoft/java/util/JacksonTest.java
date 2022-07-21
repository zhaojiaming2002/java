package net.microsoft.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.microsoft.java.web.bean.entity.User;
import org.testng.annotations.Test;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class JacksonTest {
    @Test
    public void testJacksonObject2JSON() throws JsonProcessingException {
        User tony = new User();
        tony.setId(1);
        tony.setName("tony");
        tony.setPassword("1111111");
        tony.setCreateDate(LocalDateTime.now());
        tony.setUpdateDate(LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        String userJSONString = objectMapper.writeValueAsString(tony);
        System.out.println(userJSONString);

        User jack = new User();
        jack.setId(2);
        jack.setName("jack");
        jack.setPassword("666666666");
        jack.setCreateDate(LocalDateTime.now());
        jack.setUpdateDate(LocalDateTime.now());
        List<User> userList = new ArrayList<>();
        userList.add(tony);
        userList.add(jack);
        String userListJSON = objectMapper.writeValueAsString(userList);
        System.out.println(userListJSON);


    }
}
