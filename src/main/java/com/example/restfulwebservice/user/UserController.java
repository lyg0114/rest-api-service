package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserDaoService service;

  @GetMapping(path = "/users")
  public List<User> retrieveAllUsers() {
    return service.findAll();
  }

  @GetMapping(path = "/users/{id}")
  public User retrieveAllUser(@PathVariable int id) {
    User user = service.findOne(id);
    if(user == null)
      throw new UserNotFoundException(String.format("ID[%s] not found", id));
    return service.findOne(id);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = service.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser.getId())
        .toUri();
    return ResponseEntity
        .created(location)
        .build();
  }
}