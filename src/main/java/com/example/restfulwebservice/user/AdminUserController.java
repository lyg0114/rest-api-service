package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    mapping.setFilters(getFilterProvider());
    return mapping;
  }

  @GetMapping(path = "/users/{id}")
  public MappingJacksonValue retrieveAllUser(@PathVariable int id) {
    User user = service.findOne(id);
    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(getFilterProvider());
    return mapping;
  }

  private FilterProvider getFilterProvider() {
    String[] filterStr = {"id", "name", "joinDate", "password", "ssn"};
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept(filterStr);
    return new SimpleFilterProvider()
        .addFilter("UserInfo", filter);
  }
}
