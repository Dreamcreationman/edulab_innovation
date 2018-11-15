package com.edulab.shiro;

import com.edulab.model.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CREATED BY Yank
 * DATE : 2018/8/30
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION : DBRealm for shiro
 */
public class ShiroRealm extends AuthorizingRealm {

    public ShiroRealm() {
        setAuthenticationTokenClass(UsernamePasswordToken.class);
        ShiroCredentialMatcher shiroCredentialMatcher = new ShiroCredentialMatcher();
        setCredentialsMatcher(shiroCredentialMatcher);
    }

    @Override
    public String getName() {
        return "shiroRealm";
    }

    /**
     * 处理授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String identifier = (String) principalCollection.getPrimaryPrincipal();
        int id = UserAuth.dao.getIdByIdentifier(identifier);
        Set<String> roles = UserRole.dao.getRoleNamesByUserId(id);
        Set<String> authorities = RoleRight.dao.getRightsById(id);
        SimpleAuthorizationInfo simpleAuthorizationInfo =
                new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(authorities);
        return simpleAuthorizationInfo;
    }

    /**
     * 处理认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String identifier = (String) token.getPrincipal();

        if (UserAuth.dao.isUserExists(identifier)) {
            String credentialSql = UserAuth.dao.getSaltedCredential(identifier);
            ShiroPrincipal shiroPrincipal = new ShiroPrincipal(User.dao.getUserByUserID(UserAuth.dao.getIdByIdentifier(identifier)));
            int id = UserAuth.dao.getIdByIdentifier(identifier);
            List<String> roles = new ArrayList<String>(UserRole.dao.getRoleNamesByUserId(id));
            List<String> authorities = new ArrayList<String>(RoleRight.dao.getRightsById(id));
            shiroPrincipal.setRoles(roles);
            shiroPrincipal.setAuthorities(authorities);
            shiroPrincipal.setAuthorized(true);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroPrincipal, credentialSql, getName());
            return info;
        } else{
            return null;
        }

    }


}
