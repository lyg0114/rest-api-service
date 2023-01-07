package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    return service.findOne(id);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User savedUser = service.save(user);
    return ResponseEntity
        .created(getUri(savedUser))
        .build();
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable int id) {
    service.deleteById(id);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable int id, @RequestBody User updateUser) {
    User updatedUser = service.updabeById(id, updateUser);
    return ResponseEntity
        .created(getUri(updatedUser))
        .build();
  }

  private URI getUri(User updatedUser) {
    return ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(updatedUser.getId())
        .toUri();
  }
}
