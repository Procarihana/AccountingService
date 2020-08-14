package com.procarihana.accounting.Manager;

import com.procarihana.accounting.Dao.UserInfoDao;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import com.procarihana.accounting.moudle.common.UserInfo;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        //com.Procarihana.AccountingService.Moudle.presistence.UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoById(userId))
            .orElseThrow(() -> new ResourceNotFoundException(String.format(" User %s was not found", userId)));
        UserInfo commonUserInfo = userInfoPresToComConverter.convert(userInfo);
        return commonUserInfo;
    }
}
