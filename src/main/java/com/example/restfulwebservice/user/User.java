package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
  @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
  private String name;
  @Past
  private Date joinDate;
  private Date updateDate;
  @JsonIgnore
  private String password;
  @JsonIgnore
  private String ssn;

  public void update(User updateUser) {
    if (StringUtils.hasLength(updateUser.getName())) {
      this.name = updateUser.getName();
    }
    this.updateDate = new Date();
  }
}
