package com.Procarihana.AccountingService.Dao;

import com.Procarihana.AccountingService.Dao.Mapper.UserInfoMapper;
import com.Procarihana.AccountingService.Moudle.presistence.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDaoImpl implements UserInfoDao{
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByyUserId(id) ;
    }

    @Override
    public void createNewUser(String username, String password) {
    }
}
