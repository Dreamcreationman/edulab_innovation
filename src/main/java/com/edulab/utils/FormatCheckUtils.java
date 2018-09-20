package com.edulab.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CREATED BY Dream
 * DATE : 2018/8/30
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class FormatCheckUtils {

    /**
     * 判断登录类型
     *
     * @param identifier
     * @return
     */
    public static String identifierType(String identifier) {

        String identifierType = "username";

        if (isValidPhone(identifier)) {
            identifierType = "phone";
        } else if (isValidEmail(identifier)) {
            identifierType = "email";
        } else {
            identifierType = "username";
        }
        return identifierType;
    }

    public static boolean isValidPhone(String identifier) {

        String check = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(identifier);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

    public static boolean isValidEmail(String identifier) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(identifier);
        boolean isMatched = matcher.matches();
        return isMatched;
    }

}
