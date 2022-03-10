package com.yang.shirospringboot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/09/20:55
 */
@Configuration
public class ShiroConfig {
    /**
     * 第三步创建ShiroFilterFactoryBean
     * @param defaultSecurityManager
     * @return
     */
    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);

        // 添加shiro的内置过滤器
        /**
         * anon: 无需认证就就可以访问
         * authc:必须认证了才能访问
         * user:必须拥有 "记住我" 才能访问
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 设置拦截的资源和设置可以访问资源的权限
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
        // 支持通配符
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 设置没有authc权限不能访问后，跳转值登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }
    /**
     * 第二步创建DefaultSecurityManager
     * @Qualifier("userRealm") 将spring bean的UserRealm 给了这个getDefaultSecurityManager方法
     * @Qualifier("userRealm") 里边的方法是创建bean 的方法名
     *
     * @param userRealm
     * @return
     */
    // DefaultSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();

        // DefaultSecurityManager 去关联Realm对象
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    /**
     * 第一步 创建 Realm 对象
     * @return
     */
    // 创建 Realm 对象  如果是自定义Realm，需要自己创建类 并继承AuthorizingRealm
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
