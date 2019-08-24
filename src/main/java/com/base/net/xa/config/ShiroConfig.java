package com.base.net.xa.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: zhaikaixuan
 * @Date: 2019-08-20 13:13
 * @Description: TODO
 */
public class ShiroConfig {
    /**
     * ShiroFilterFacctoryBean
     */
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager
     */
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("baseRealm") BaseRealm baseRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(baseRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean
    public BaseRealm getRealm() {
        BaseRealm baseRealm = new BaseRealm();
//        baseRealm.setCredentialsMatcher();
        return baseRealm;
    }


    /**
     * 密码加密配置
     */
    @Bean
    public HashedCredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);

        return hashedCredentialsMatcher;
    }
}
