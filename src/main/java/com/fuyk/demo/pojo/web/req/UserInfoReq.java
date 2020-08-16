package com.fuyk.demo.pojo.web.req;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class UserInfoReq {
    @NotBlank
    private String name;
}
