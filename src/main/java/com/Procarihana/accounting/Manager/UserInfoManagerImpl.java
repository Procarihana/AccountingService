package com.procarihana.accounting.Manager;

import com.procarihana.accounting.Dao.UserInfoDao;
import com.procarihana.accounting.converter.presisitenceToCommon.UserInfoPresToComConverter;
import com.procarihana.accounting.exception.InvalidParameterException;
import com.procarihana.accounting.exception.ResourceNotFoundException;
import com.procarihana.accounting.moudle.common.UserInfo;

import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    public static final int HASH_ITERATIONS = 1000;
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

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoByUsername(username))
            .orElseThrow(() -> new ResourceNotFoundException(String.format("User name %s was not found", username)));
        UserInfo commonUserInfo = userInfoPresToComConverter.convert(userInfo);
        return commonUserInfo;
    }

    @Override
    public void login(String username, String password) {
        //Get Subject
        val subject = SecurityUtils.getSubject();
        //Construct token
        val usernamePasswordToken = new UsernamePasswordToken(username, password);
        //login
        subject.login(usernamePasswordToken);
    }

    @Override
    public UserInfo register(String username, String password) {
        val userInfo = userInfoDao.getUserInfoByUsername(username);
        if (userInfo != null) {
            throw new InvalidParameterException(String.format("The user %s was registered."));
        }

        // Set random salt
        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();
        val newUserInfo = com.procarihana.accounting.moudle.presistence.UserInfo.builder()
            .username(username)
            .password(encryptedPassword)
            .salt(salt)
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build();
        userInfoDao.createNewUser(newUserInfo);
        return userInfoPresToComConverter.convert(newUserInfo);

    }

}
