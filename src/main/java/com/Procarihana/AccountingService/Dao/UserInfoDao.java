package com.Procarihana.AccountingService.Dao;

import com.Procarihana.AccountingService.Moudle.presistence.UserInfo;

public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);
    void createNewUser(String username,String password);
}
