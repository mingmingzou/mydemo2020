package com.mydemo.demo.common.Exception;


import com.mydemo.demo.common.enums.Errcode;
import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {

    private Integer errcode;
    private String errmsg;


    public ServerException(Errcode errcode) {
        this.errcode = errcode.getErrcode();
        this.errmsg = errcode.getErrmsg();
    }

    public ServerException(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
