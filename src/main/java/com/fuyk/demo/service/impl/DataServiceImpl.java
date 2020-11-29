package com.fuyk.demo.service.impl;

import com.fuyk.demo.pojo.web.req.DataReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.service.DataService;
import com.fuyk.demo.sqlservice.dao.EventPropertyMappingMapper;
import com.fuyk.demo.sqlservice.domain.EventPropertyMapping;
import com.fuyk.demo.sqlservice.domain.EventPropertyMappingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    @Autowired(required = false)
    private EventPropertyMappingMapper eventPropertyMappingMapper;

    @Override
    public BaseResultRsp upload(DataReq dataReq){
        return this.uploadToMysql(dataReq);
    }

    public BaseResultRsp uploadToMysql(DataReq dataReq){
        EventPropertyMappingQuery eventPropertyMappingQuery = new EventPropertyMappingQuery();
        EventPropertyMappingQuery.Criteria criteria = eventPropertyMappingQuery.createCriteria();
        criteria.andAppidEqualTo(dataReq.getAppId()).andEventidEqualTo(dataReq.getEventId()).andOsEqualTo(dataReq.getOs());
        //插入实体类
        EventPropertyMapping eventPropertyMapping1 = new EventPropertyMapping();
        eventPropertyMapping1.setAppid(dataReq.getAppId());
        eventPropertyMapping1.setEventid(dataReq.getEventId());
        eventPropertyMapping1.setOs(dataReq.getOs());
        eventPropertyMapping1.setProperty(dataReq.getProperty());
        //更新实体类
        EventPropertyMapping eventPropertyMapping2 = new EventPropertyMapping();
        eventPropertyMapping2.setProperty(dataReq.getProperty());

        if(eventPropertyMappingMapper.countByExample(eventPropertyMappingQuery) == 0){

            eventPropertyMappingMapper.insert(eventPropertyMapping1);
            uploadToRedis(dataReq);
            return new BaseResultRsp("insert success");
        }
        else{
            criteria.andPropertyEqualTo(dataReq.getProperty());
            if(eventPropertyMappingMapper.countByExample(eventPropertyMappingQuery) == 0){
                eventPropertyMappingQuery.clear();
                eventPropertyMappingQuery.createCriteria().andAppidEqualTo(dataReq.getAppId()).andEventidEqualTo(dataReq.getEventId()).andOsEqualTo(dataReq.getOs());
                eventPropertyMappingMapper.updateByExampleSelective(eventPropertyMapping2,eventPropertyMappingQuery);
                uploadToRedis(dataReq);
                return new BaseResultRsp("update success");
            }
            else{
                uploadToRedis(dataReq);
                return new BaseResultRsp("already exits");
            }

        }
    }
    public void uploadToRedis(DataReq dataReq){

    }
}
