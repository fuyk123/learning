package com.fuyk.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuyk.demo.pojo.web.req.AddUserInfoReq;
import com.fuyk.demo.pojo.web.rsp.BaseResultRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoListRsp;
import com.fuyk.demo.pojo.web.rsp.UserInfoRsp;
import com.fuyk.demo.service.UserInfoService;
import com.fuyk.demo.sqlservice.dao.UserMapper;
import com.fuyk.demo.sqlservice.domain.User;
import com.fuyk.demo.sqlservice.domain.UserQuery;
import com.fuyk.demo.util.LogUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserInfoRsp queryUserInfo(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                    .id(user.getId())
                    .name(user.getUserName())
                    .sex(user.getSex())
                    .isLocal((user.getLocal() == 1 ? true : false))
                    .errmsg("success")
                    .build();
            return userInfoRsp;
        }
        else{
            UserInfoRsp userInfoRsp = UserInfoRsp.builder()
                    .id(null)
                    .name(null)
                    .sex(null)
                    .isLocal(null)
                    .errmsg("无此id")
                    .build();
            return userInfoRsp;
        }
    }

    @Override
    public PageInfo<User> queryUserInfoByName(String name, Integer pageNo, Integer pageSize) throws JsonProcessingException {
        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUserNameEqualTo(name);
        //PageHelper类的使用
        PageHelper.startPage(pageNo,pageSize);
        List<User> userList = userMapper.selectByExample(userQuery);
        //结果集放到PageInfo<T> 泛型集合类中
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //打印返回参数
        LogUtil.logUtil(pageInfo,1);
        return pageInfo;
    }

    @Override
    public UserInfoListRsp queryUserInfoByNameLimit(String name,Integer limit){
        UserQuery userQuery = new UserQuery();
        userQuery.setLimitNum(limit);
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<User> userList = userMapper.selectByExampleLimit(userQuery);
        UserInfoListRsp userInfoListRsp = new UserInfoListRsp();
        userInfoListRsp.setUserInfoRspList(
                userList.stream().map(userInfo ->UserInfoRsp.builder().
                        id(userInfo.getId())
                        .isLocal(userInfo.getLocal()==1?true:false)
                        .sex(userInfo.getSex())
                        .name(userInfo.getUserName())
                        .build()).collect(Collectors.toList())
        );
        return userInfoListRsp;
    }

    public UserInfoListRsp queryUserInfoByNamePage(String name,Integer pageNo,Integer pageSize){
        UserQuery userQuery = new UserQuery();
        userQuery.setLimitFront((pageNo-1)*pageSize);
        userQuery.setLimitAfter(pageSize);
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUserNameEqualTo(name);
        List<User> userList = userMapper.selectByExamplePage(userQuery);
        //这个波浪线表示下面代码重复，建议提出为一个公共method
        UserInfoListRsp userInfoListRsp = new UserInfoListRsp();
        userInfoListRsp.setUserInfoRspList(
                userList.stream().map(userInfo ->UserInfoRsp.builder().
                        id(userInfo.getId())
                        .isLocal(userInfo.getLocal()==1?true:false)
                        .sex(userInfo.getSex())
                        .name(userInfo.getUserName())
                        .build()).collect(Collectors.toList())
        );
        return userInfoListRsp;
    }

    @Override
    public BaseResultRsp addUserInfo(AddUserInfoReq addUserInfoReq){
        BaseResultRsp baseResultRsp = new BaseResultRsp();
        User user = new User();
        user.setUserName(addUserInfoReq.getUserName());
        user.setSex(addUserInfoReq.getSex());
        user.setLocal(addUserInfoReq.getLocal());
        baseResultRsp.setErrmsg(userMapper.insert(user)>0?"success":"failed");
        return baseResultRsp;
    }
}
