package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public class UserFilterProvider {
  public FilterProvider getFilterProviderV1() {
    String[] filterStr = {"id", "name", "joinDate", "password", "ssn"};
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept(filterStr);
    return new SimpleFilterProvider()
        .addFilter("UserInfo", filter);
  }

  public FilterProvider getFilterProviderV2() {
    String[] filterStr = {"id", "name", "joinDate", "grade"};
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept(filterStr);
    return new SimpleFilterProvider()
        .addFilter("UserInfoV2", filter);
  }
}
