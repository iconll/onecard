package com.onecard.system.common.config.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring-security登陆的密码进行MD5加密传到数据库
 * TODO @娄雷，现在一般不推荐用MD5，官方推荐的是BCryptPasswordEncoder，你看下面方法标记过时其实是因为官方认为其并不安全
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(rawPassword.toString(), "onecard");
    }
    /**
     * 判断密码是否一致
     * @param rawPassword 没有经过加密的密码
     * @param encodedPassword 经过加密的密码
     * @return boolean 比较结果:使用的equals进行比较
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.isPasswordValid(encodedPassword, rawPassword.toString(), "onecard");
    }

}
