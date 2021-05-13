package com.mydemo.demo.modules.sys.login.entity.DTO;


import lombok.Data;

import java.io.Serializable;


@Data
public class LoginDTO implements Serializable {

  private String loginId;
  private String username;
  private String password;
  private String phone;
}
