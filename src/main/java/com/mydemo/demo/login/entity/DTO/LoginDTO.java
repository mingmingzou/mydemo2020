package com.mydemo.demo.login.entity.DTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户登录类")
@Data
public class LoginDTO implements Serializable {

  @ApiModelProperty(value = "手机号", required = true)
  private String password;

  @ApiModelProperty(value = "手机号", required = true)
  private String phone;
}
