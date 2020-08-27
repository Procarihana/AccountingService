package com.procarihana.accounting.config;

import lombok.val;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    /**
     * Shiro Filter,实现权限相关拦截.
     * anon: Not need login access
     * authc: required login ,and then access
     * user: remember me -> access
     * role: role -> access
     */

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        val shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //控制url用什么权限访问，常用LinkedHashMap
        val shiroFilterDefinitionMap = new LinkedHashMap<String, String>();
        //map需要顺序，因为有'/**'的设置，如果没有顺序，容易造成`/**`控制所有的页面，其他的权限控制无效的情况

        //@Todo:consider different HTTP method may need differetn  filter
        shiroFilterDefinitionMap.put("/v1.0/greeting", "authc"); //需要登录才能访问
        shiroFilterDefinitionMap.put("/v1.0/users/**", "anon");
        //用同一个URL 'post'->anon, `get` -> authc
        shiroFilterDefinitionMap.put("/v1.0/session", "anon");
        shiroFilterDefinitionMap.put("/v1.0/tags/**", "anon");//不需要登录就能访问
        shiroFilterDefinitionMap.put("/**", "authc");//其他的页面需要登录才能够访问

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Password add iteration salt.
     *
     * @return encrypting password
     */
    @Bean
    public HashedCredentialsMatcher matcher() {
        final Integer iterationsTimes = 1000;
        val matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");//加盐的算法名字注意大小写
        matcher.setHashIterations(iterationsTimes);//加盐循环的次数
        matcher.setHashSalted(true);
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;


    }
}
