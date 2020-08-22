package com.fuyk.demo.pojo.web.rsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder//这是个static方法，不是实例方法
@Data
//自动生成全参构造方法
@AllArgsConstructor
//自动生成无参构造方法
@NoArgsConstructor
public class UserInfoRsp {
    private String errmsg;
    private Integer id;
    private String name;
    private String sex;
    private Boolean isLocal;

}
