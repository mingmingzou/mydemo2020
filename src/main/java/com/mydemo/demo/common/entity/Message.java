package com.mydemo.demo.common.entity;


import com.mydemo.demo.common.enums.Errcode;
import lombok.Data;


@Data
public class Message<T> {

    private Integer errcode;
    private String errmsg;
    private T data;

    public Message<T> ok() {
        return ok(null);
    }
    public Message<T> ok(T t) {
        this.errcode = Errcode.ok.getErrcode();
        this.errmsg = Errcode.ok.getErrmsg();
        this.data = t;
        return this;
    }
    public Message<T> ok(Integer errcode, String errmsg, T t) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = t;
        return this;
    }
    public Message<T> error(String errmsg, T t) {
        this.errcode = Errcode.error.getErrcode();
        this.errmsg = errmsg == null ? Errcode.error.getErrmsg() : errmsg;
        this.data = t;
        return this;
    }
    public Message<T> error(String errmsg) {
        return error(errmsg, null);
    }


    public Message<T> rest(Integer errcode, String errmsg, T t) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = t;
        return this;
    }
    public Message<T> rest(Integer errcode, String errmsg) {
        return rest(errcode, errmsg, null);
    }
    public Message<T> rest(Errcode errcode, T t) {
        return rest(errcode.getErrcode(), errcode.getErrmsg(), null);
    }
    public Message<T> rest(Errcode errcode) {
        return rest(errcode, null);
    }


    public Message<T> addError() {
        return rest(Errcode.E_2001, null);
    }
    public Message<T> uptError() {
        return rest(Errcode.E_2003, null);
    }
    public Message<T> selError() {
        return rest(Errcode.E_2004, null);
    }
    public Message<T> delError() {
        return rest(Errcode.E_2002, null);
    }


}