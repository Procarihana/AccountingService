package com.procarihana.accounting.config;

import com.procarihana.accounting.Manager.UserInfoManager;

import lombok.val;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    private final UserInfoManager userInfoManager; //链接数据库

    /**
     * Authenticated user.
     *
     * @param userInfoManager Get the user information from database bu username.
     * @param matcher         Verify if the password add iterative salt is correct.
     */
    @Autowired
    public UserRealm(UserInfoManager userInfoManager,
                     HashedCredentialsMatcher matcher) {
        //matcher:用于和shiroConfig的加密密码关联起来
        super(matcher);
        this.userInfoManager = userInfoManager;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); //主项
        //String password = new String((char[]) token.getCredentials()); //凭证
        val userInfo = userInfoManager.getUserInfoByUsername(username);
        val salt = ByteSource.Util.bytes(userInfo.getSalt());
        return new SimpleAuthenticationInfo(userInfo.getUsername(), userInfo.getPassword(), salt, this.getName()); //认证
    }
}
