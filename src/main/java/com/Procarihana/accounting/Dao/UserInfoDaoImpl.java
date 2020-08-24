package com.procarihana.accounting.Dao;

import com.procarihana.accounting.Dao.Mapper.UserInfoMapper;
import com.procarihana.accounting.moudle.presistence.UserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDaoImpl implements UserInfoDao {
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByyUserId(id);
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userInfoMapper.gerUserInfoByUsername(username);
    }

    @Override
    public void createNewUser(UserInfo userInfo) {
        val row = userInfoMapper.createNewUser(userInfo);
        log.debug("Result: {},user information: {}", row, userInfo);
    }
}
