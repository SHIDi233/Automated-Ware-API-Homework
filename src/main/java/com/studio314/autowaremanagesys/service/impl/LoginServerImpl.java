//package com.studio314.autowaremanagesys.service.impl;
//
//import com.studio314.autowaremanagesys.config.RedisCache;
//import com.studio314.autowaremanagesys.mapper.UserMapper;
//import com.studio314.autowaremanagesys.pojo.LoginUser;
//import com.studio314.autowaremanagesys.pojo.MyUser;
//import com.studio314.autowaremanagesys.service.LoginService;
//import com.studio314.autowaremanagesys.utils.JWTUtils;
//import com.studio314.autowaremanagesys.utils.Result;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//@Slf4j
//public class LoginServerImpl implements LoginService {
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    UserMapper userMapper;
//
//    @Override
//    public Result login(String mail, String password) {
//        log.info("用户尝试登录server：" + mail);
//        //AuthenticationManager authenticate 进行用户认证
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mail, password);
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        //如果认证没通过，给出对应的提示
//        if (Objects.isNull(authenticate)){
////            throw new RuntimeException("登录失败");
//            log.error("用户尝试登录，登录失败：" + mail);
//            return Result.error("登录失败");
//        }
//        log.info("用户认证成功");
//        //如果认证通过了，使用userid生成一个jwt jwt存入ajax
//        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
//        int userId = loginUser.getUser().getUID();
//        Map<String,Object> map = new HashMap<>();
//        map.put(JWTUtils.DETAILS_USER_ID,userId);
//        String token = JWTUtils.createToken(map);
//        log.info("生成token成功：" + token);
//        //把完整的用户信息存入redis userid作为key
//        redisCache.setCacheObject("login:"+userId,loginUser);
//        log.info("用户登录成功：" + mail);
//        Map<String, Object> result = new HashMap<>();
//        result.put("token", token);
//        return Result.success(result);
//    }
//
//    @Override
//    public Result register(String name, String mail, String password) {
//        String encodePassword = bCryptPasswordEncoder.encode(password);
//        userMapper.registerUser(name, mail, encodePassword);
//        log.info("用户注册成功：" + mail);
//        return Result.success("注册成功");
//    }
//
//
//}

package com.studio314.autowaremanagesys.service.impl;
import com.studio314.autowaremanagesys.service.LoginService;
import com.studio314.autowaremanagesys.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServerImpl implements LoginService {


    @Override
    public Result login(String mail, String password) {
        log.info("用户尝试登录server：" + mail);

        return Result.error("登录失败v2");
    }

    @Override
    public Result register(String name, String mail, String password) {
        return Result.success("注册成功");
    }
}
