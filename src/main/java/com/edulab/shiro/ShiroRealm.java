package com.edulab.shiro;

import com.edulab.model.UserAuths;
import com.edulab.model.UserRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * CREATED BY Dream
 * DATE : 2018/8/30
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class ShiroRealm extends AuthorizingRealm {

    public ShiroRealm() {
        setAuthenticationTokenClass(UsernamePasswordToken.class);
        ShiroCredentialMatcher shiroCredentialMatcher = new ShiroCredentialMatcher();
        setCredentialsMatcher(shiroCredentialMatcher);
    }

    public String getName() {
        return "shiroRealm";
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String identifier = (String) principalCollection.getPrimaryPrincipal();
        int id = UserAuths.dao.getIdByIdentifier(identifier);
        Set<String> roles = UserRole.dao.getRolesById(id);
        SimpleAuthorizationInfo simpleAuthorizationInfo =
                new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 处理认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String identifier = (String) token.getPrincipal();

        if (UserAuths.dao.isUserExists(identifier)) {
            String credentialSql = UserAuths.dao.getSaltedCredential(identifier);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(identifier, credentialSql, getName());
            return info;
        } else
            return null;

    }


}
