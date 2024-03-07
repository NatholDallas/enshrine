package nathol.utils.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import nathol.spring.validation.Validator;
import nathol.utils.App;
import nathol.utils.data.ResponseEntity;
import nathol.utils.data.Serializers.LoginIn;
import nathol.utils.data.Serializers.RegisterIn;
import nathol.utils.mapper.UserMapper;
import nathol.utils.model.User;

@RestController
public final class AccountController {

    @Autowired
    private UserMapper userMapper;

    @Operation(description = "注册")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterIn data) {
        User user = new User();
        user.setEmail(data.email());
        user.setUsername(data.username());
        user.setPassword(data.password());
        userMapper.insert(user);
        return ResponseEntity.success();
    }

    @Operation(description = "登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginIn data) {
        UserMapper userMapper = App.context.getBean(UserMapper.class);
        Wrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", data.username())
                .eq("password", data.password());
        User user = userMapper.selectOne(wrapper);
        Validator.notNull(user);
        StpUtil.login(user.getId());
        return ResponseEntity.success();
    }

    @SaCheckLogin
    @Operation(description = "登出")
    @PostMapping("/logout")
    public ResponseEntity logout() {
        StpUtil.logout();
        return ResponseEntity.success();
    }

}
