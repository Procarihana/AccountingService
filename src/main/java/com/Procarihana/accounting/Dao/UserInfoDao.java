package com.procarihana.accounting.Dao;

import com.procarihana.accounting.moudle.presistence.UserInfo;

public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);
    void createNewUser(String username,String password);
}
