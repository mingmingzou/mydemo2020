package com.mydemo.demo.login.entity.PO;


import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {

  private String loginId;
  private String username;
  private String password;
  private String phone;
}
