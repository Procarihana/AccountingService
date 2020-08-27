package com.procarihana.accounting.Dao;

import com.procarihana.accounting.moudle.presistence.UserInfo;

public interface UserInfoDao {
    UserInfo getUserInfoById(Long id);

    UserInfo getUserInfoByUsername(String username);

    void createNewUser(UserInfo userInfo); //通过使用数据模型来防止参数不断增加导致数据传输错误的情况


}
