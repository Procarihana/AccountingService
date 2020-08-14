package com.procarihana.accounting.Dao.Mapper;


import com.procarihana.accounting.moudle.presistence.UserInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Select("SELECT id, username, password, create_time, update_time FROM accounting_userinfo where id =#{id}")
    UserInfo getUserInfoByyUserId(@Param("id")Long id);

}

