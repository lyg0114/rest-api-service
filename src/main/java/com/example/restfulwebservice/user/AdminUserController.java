package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

  private final UserDaoService service;

  @GetMapping(path = "/users")
  public MappingJacksonValue retrieveAllUsers() {
    List<User> users = service.findAll();
    MappingJacksonValue mapping = new MappingJacksonValue(users);
    mapping.setFilters(getFilterProviderV1());
    return mapping;
  }

//  @GetMapping(path = "/v1/users/{id}")
//  @GetMapping(path = "/users/{id}/", params = "version=1")
  @GetMapping(path = "/users/{id}", headers = "X-API-VERSION=1")
  public MappingJacksonValue retrieveAllUserV1(@PathVariable int id) {
    User user = service.findOne(id);
    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(getFilterProviderV1());
    return mapping;
  }

//  @GetMapping(path = "/v2/users/{id}/")
//  @GetMapping(path = "/users/{id}/", params = "version=2")
  @GetMapping(path = "/users/{id}", headers = "X-API-VERSION=2")
  public MappingJacksonValue retrieveAllUserV2(@PathVariable int id) {
    User user = service.findOne(id);
    UserV2 userV2 = new UserV2();
    BeanUtils.copyProperties(user, userV2);
    userV2.setGrade("VIP");

    MappingJacksonValue mapping = new MappingJacksonValue(userV2);
    mapping.setFilters(getFilterProviderV2());
    return mapping;
  }

  private FilterProvider getFilterProviderV1() {
    String[] filterStr = {"id", "name", "joinDate", "password", "ssn"};
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept(filterStr);
    return new SimpleFilterProvider()
        .addFilter("UserInfo", filter);
  }

  private FilterProvider getFilterProviderV2() {
    String[] filterStr = {"id", "name", "joinDate", "grade"};
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept(filterStr);
    return new SimpleFilterProvider()
        .addFilter("UserInfoV2", filter);
  }
}
