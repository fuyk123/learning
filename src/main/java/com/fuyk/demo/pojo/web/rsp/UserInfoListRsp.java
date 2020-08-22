package com.fuyk.demo.pojo.web.rsp;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoListRsp {
    private List<UserInfoRsp> userInfoRspList;
}
