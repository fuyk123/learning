package com.fuyk.demo.pojo.web.req;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class UserInfoReq {
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer limit;
    private Integer pageNo;
    private Integer pageSize;
}
