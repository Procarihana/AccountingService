package com.Procarihana.AccountingService.Manager;

import com.Procarihana.AccountingService.Dao.UserInfoDao;
import com.Procarihana.AccountingService.Moudle.common.UserInfo;

import com.Procarihana.AccountingService.converter.presisitenceToCommon.UserInfoPresToComConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    private final UserInfoDao userInfoDao;
    private final UserInfoPresToComConverter userInfoPresToComConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDao userInfoDao,
                               final UserInfoPresToComConverter userInfoPresToComConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoPresToComConverter = userInfoPresToComConverter;
    }


    @Override
    public UserInfo getUserInfoByUserID(Long userId) {
        com.Procarihana.AccountingService.Moudle.presistence.UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        UserInfo commonUserInfo = userInfoPresToComConverter.convert(userInfo);
        return commonUserInfo;
    }
}
