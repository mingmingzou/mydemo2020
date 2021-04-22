package com.mydemo.demo.common.enums;


/**
 * 全局错误返回码
 */
public enum Errcode {


    error(-1, "系统错误，请稍后再试"),
    ok(0, "ok"),

    E_1001(1001, "请先登录"),
    E_1002(1002, "您没有接口权限"),
    E_1003(1003, "登录已失效，请重新登录"),
    E_1004(1004, "token已失效"),
    E_1005(1005, "用户认证失败"),
    E_1006(1006, "无效的token"),
    E_1007(1007, "无效的签名"),


    E_2001(2001, "新增失败"),
    E_2002(2002, "删除失败"),
    E_2003(2003, "修改失败"),
    E_2004(2004, "查不到数据"),
    E_2005(2005, "参数不能为空"),
    E_2006(2006, "发送失败，请稍后再试"),
    E_2007(2007, "非法参数"),
    E_2008(2008, "参数不能为空"),
    E_2009(2009, "请求方法不支持"),
    E_2010(2010, "sql执行错误"),
    E_2011(2011, "名称已存在"),
    E_2012(2012, "部分参数为空"),
    E_2013(2013, "io错误"),
    E_2014(2014, "文件未找到"),
    E_2015(2015, "数组越界错误"),
    E_2016(2016, "类型强转错误"),
    E_2017(2017, "空指针错误"),


    U_3001(3001, "用户名或密码错误"),
    U_3002(3002, "用户名不能为空"),
    U_3003(3003, "密码不能为空"),
    U_3004(3004, "验证码错误"),
    U_3005(3005, "用户已被禁用"),
    U_3006(3006, "用户名不存在"),



    E_4001(4001, "店铺不存在"),
    E_4002(4002, "店铺已被禁用"),
    E_4003(4003, "店铺已被删除"),
    E_4004(4004, "店铺余额不足"),




    E_9999(9999, "未知错误");



    private Integer errcode;
    private String errmsg;

    Errcode(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }

    public Errcode setErrcode(Integer errcode) {
        this.errcode = errcode;
        return this;
    }
    public Errcode setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public static Errcode getByErrmsg(String errmsg) {
        Errcode[] values = Errcode.values();
        for (Errcode errcode : values) {
            if(errcode.getErrmsg().equals(errmsg))
                return errcode;
        }
        return Errcode.error.setErrmsg(errmsg);
    }

}
