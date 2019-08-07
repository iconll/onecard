package com.onecard.system.common.config.security;


import com.onecard.system.sys.dao.UserDao;
import com.onecard.system.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class CustomUserService implements UserDetailsService {


    @Inject
    private UserDao userDao;
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByLogin(s);
            if(user == null){
                  throw new UsernameNotFoundException("用户不存在");
            }
            // 自定义错误的文章说明的地址：http://blog.csdn.net/z69183787/article/details/21190639?locationNum=1&fps=1
            if(user.getState().equalsIgnoreCase("0")){

                    throw new LockedException("用户账号被冻结，无法登陆请联系管理员！");

            }
        return user;
    }
}
