package com.mydemo.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "返回类")
public class Rest<T> {

    @ApiModelProperty(value = "返回码")
    private Integer errcode;
    @ApiModelProperty(value = "返回信息")
    private String errmsg;
    @ApiModelProperty(value = "返回对象")
    private T data;

    public Rest<T> ok() {
        return ok(null);
    }
    public Rest<T> ok(T t) {
        this.errcode = Errcode.ok.getErrcode();
        this.errmsg = Errcode.ok.getErrmsg();
        this.data = t;
        return this;
    }
    public Rest<T> ok(Integer errcode, String errmsg, T t) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = t;
        return this;
    }
    public Rest<T> error(String errmsg, T t) {
        this.errcode = Errcode.error.getErrcode();
        this.errmsg = errmsg == null ? Errcode.error.getErrmsg() : errmsg;
        this.data = t;
        return this;
    }
    public Rest<T> error(String errmsg) {
        return error(errmsg, null);
    }


    public Rest<T> rest(Integer errcode, String errmsg, T t) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = t;
        return this;
    }
    public Rest<T> rest(Integer errcode, String errmsg) {
        return rest(errcode, errmsg, null);
    }
    public Rest<T> rest(Errcode errcode, T t) {
        return rest(errcode.getErrcode(), errcode.getErrmsg(), null);
    }
    public Rest<T> rest(Errcode errcode) {
        return rest(errcode, null);
    }


    public Rest<T> addError() {
        return rest(Errcode.E_2001, null);
    }
    public Rest<T> uptError() {
        return rest(Errcode.E_2003, null);
    }
    public Rest<T> selError() {
        return rest(Errcode.E_2004, null);
    }
    public Rest<T> delError() {
        return rest(Errcode.E_2002, null);
    }


}