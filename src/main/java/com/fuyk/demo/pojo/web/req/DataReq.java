package com.fuyk.demo.pojo.web.req;

import lombok.Data;

@Data
public class DataReq {
    private String gtcid;
    private String appId;
    private Integer os;
    private String eventId;
    private String property;
}
