package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
  private static List<User> users = new ArrayList<User>();
  private static int userConunt = 3;

  static {
    users.add(User.builder().id(1).name("user1").joinDate(new Date()).build());
    users.add(User.builder().id(2).name("user2").joinDate(new Date()).build());
    users.add(User.builder().id(3).name("user3").joinDate(new Date()).build());
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
    throw new UserNotFoundException(String.format("ID[ %s ] not found", id));
  }

  public User deleteById(int id){
    Iterator<User> itr = users.iterator();
    while(itr.hasNext()){
      User user = itr.next();
      if(user.getId() == id){
        itr.remove();
        return user;
      }
    }
    throw new UserNotFoundException(String.format("ID[ %s ] not found", id));
  }

  public User updabeById(int id, User user){
    Iterator<User> itr = users.iterator();
    while(itr.hasNext()){
      User targetUser = itr.next();
      if(targetUser.getId() == id){
        targetUser.update(user);
        return targetUser;
      }
    }
    throw new UserNotFoundException(String.format("ID[ %s ] not found", id));
  }
}
