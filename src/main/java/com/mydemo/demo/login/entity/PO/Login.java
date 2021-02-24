package com.mydemo.demo.login.entity.PO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户登录类")
@Data
public class Login implements Serializable {


  private String loginId;

  private String username;

  @ApiModelProperty(value = "手机号", required = true)
  private String phone;

  @ApiModelProperty(value = "手机号", required = true)
  private String password;


}
