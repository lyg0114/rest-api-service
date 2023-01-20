package com.example.restfulwebservice.user;

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
  private final UserFilterProvider filterProvider;

  @GetMapping(path = "/users")
  public MappingJacksonValue retrieveAllUsers() {
    List<User> users = service.findAll();
    MappingJacksonValue mapping = new MappingJacksonValue(users);
    mapping.setFilters(filterProvider.getFilterProviderV1());
    return mapping;
  }

  @GetMapping(path = "/v1/users/{id}")
  public MappingJacksonValue retrieveAllUserV1(@PathVariable int id) {
    User user = service.findOne(id);
    MappingJacksonValue mapping = new MappingJacksonValue(user);
    mapping.setFilters(filterProvider.getFilterProviderV1());
    return mapping;
  }

  @GetMapping(path = "/v2/users/{id}/")
  public MappingJacksonValue retrieveAllUserV2(@PathVariable int id) {
    User user = service.findOne(id);
    MappingJacksonValue mapping = new MappingJacksonValue(user.convertToV2());
    mapping.setFilters(filterProvider.getFilterProviderV2());
    return mapping;
  }
}
