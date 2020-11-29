package com.fuyk.demo.pojo.web.req;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;

@Builder
@Data
public class AddUserInfoReq {
    @NotBlank
    private String userName;
    @NotBlank
    private String sex;
    @NotNull
    private Integer local;
}
