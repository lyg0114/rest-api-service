package com.example.restfulwebservice.user;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Integer id;
  private String name;
  private Date joinDate;
  private Date updateDate;

  public void update(User updateUser) {
    if (StringUtils.hasLength(updateUser.getName())) {
      this.name = updateUser.getName();
    }
    this.updateDate = new Date();
  }
}
