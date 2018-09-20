package com.edulab.shiro;

import com.edulab.model.UserAuths;
import com.edulab.utils.CryptoUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * CREATED BY Dream
 * DATE : 2018/8/31
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class ShiroCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String identifier = String.valueOf(token.getPrincipal());
        Object realCredentials = getCredentials(info);
        return CryptoUtils.verify(String.valueOf(realCredentials), String.valueOf(usernamePasswordToken.getPassword()), UserAuths.dao.getSaltSQL(identifier));
    }

}
