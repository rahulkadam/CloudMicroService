package com.example.tutorialclient.controller;

import com.example.tutorialclient.component.TutorialClientChannel;
import com.example.tutorialclient.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Source source;

    public Collection<String> getNamesFallback() {
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "getNamesFallback")
    @RequestMapping(method = RequestMethod.GET , value = "/names")
    public Collection<String> names() {
        ParameterizedTypeReference<List<User>> ptr = new ParameterizedTypeReference<List<User>>() {};

        ResponseEntity<List<User>> userList = this.restTemplate.exchange("http://reservation-service/user/users" ,
                HttpMethod.GET ,null ,ptr);
        return userList.getBody().stream().map(User::getName).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {

    }
}
