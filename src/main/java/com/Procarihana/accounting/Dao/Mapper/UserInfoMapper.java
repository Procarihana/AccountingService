package com.procarihana.accounting.Dao.Mapper;


import com.procarihana.accounting.moudle.presistence.UserInfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT id, username, password, salt, create_time, update_time FROM accounting_userinfo where id =#{id}")
    UserInfo getUserInfoByyUserId(@Param("id") Long id);

    @Select("SELECT id, username, password, salt, create_time, update_time "
        + "FROM accounting_userinfo WHERE username =#{username}")
    UserInfo gerUserInfoByUsername(@Param("username") String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    //id自增
    @Insert("INSERT into accounting_userinfo(username, password, salt, create_time, update_time) "
        + "VALUES (#{username}, #{password}, #{salt}, #{createTime}, #{updateTime})")
    int createNewUser(UserInfo userInfo);  //int -> 表示新user的个数
}

