package com.fuyk.demo.pojo.web.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddUserInfoReq {
    @NotBlank
    private String userName;
    @NotBlank
    private String sex;
    @NotNull
    private Integer local;
}
