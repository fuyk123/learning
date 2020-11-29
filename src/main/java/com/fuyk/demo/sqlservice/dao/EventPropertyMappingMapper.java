package com.fuyk.demo.sqlservice.dao;

import com.fuyk.demo.sqlservice.domain.EventPropertyMapping;
import com.fuyk.demo.sqlservice.domain.EventPropertyMappingQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventPropertyMappingMapper {
    long countByExample(EventPropertyMappingQuery example);

    int deleteByExample(EventPropertyMappingQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventPropertyMapping record);

    int insertSelective(EventPropertyMapping record);

    List<EventPropertyMapping> selectByExample(EventPropertyMappingQuery example);

    EventPropertyMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventPropertyMapping record, @Param("example") EventPropertyMappingQuery example);

    int updateByExample(@Param("record") EventPropertyMapping record, @Param("example") EventPropertyMappingQuery example);

    int updateByPrimaryKeySelective(EventPropertyMapping record);

    int updateByPrimaryKey(EventPropertyMapping record);
}