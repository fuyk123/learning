package com.fuyk.demo.pojo.web.rsp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoRsp {
    private Integer id;
    private String name;
    private String sex;
    private Boolean isLocal;
}
