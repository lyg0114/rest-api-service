package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoService {

  private static List<User> users = new ArrayList<User>();
  private static int userConunt = 3;

  static {
    users.add(new User(1, "user1", new Date()));
    users.add(new User(2, "user2", new Date()));
    users.add(new User(3, "user3", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (user.getId() == null) {
      user.setId(++userConunt);
    }
    users.add(user);
    return user;
  }

  public User findOne(int id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }

}
