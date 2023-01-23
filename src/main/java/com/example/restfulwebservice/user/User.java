package com.example.restfulwebservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
//@JsonFilter("UserInfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Integer id;

  @ApiModelProperty(notes = "사용자 이름을 입력해 주세요 ")
  @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
  private String name;

  @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요 ")
  @Past
  private Date joinDate;

  private Date updateDate;

  @ApiModelProperty(notes = "사용자 패스워드를 입력해 주세요 ")
  private String password;

  @ApiModelProperty(notes = "사용자 주민번호를 입력해 주세요 ")
  private String ssn;

  public void update(User updateUser) {
    if (StringUtils.hasLength(updateUser.getName())) {
      this.name = updateUser.getName();
    }
    this.updateDate = new Date();
  }

  public UserV2 convertToV2() {
    UserV2 userV2 = new UserV2();
    BeanUtils.copyProperties(this, userV2);
    userV2.setGrade("VIP");
    return userV2;
  }
}
