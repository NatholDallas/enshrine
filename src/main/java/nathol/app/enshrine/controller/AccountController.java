package nathol.app.enshrine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import nathol.app.enshrine.mapper.EmailMapper;
import nathol.app.enshrine.mapper.UserMapper;
import nathol.app.enshrine.model.User;
import nathol.app.enshrine.request.LoginIn;
import nathol.app.enshrine.request.RegisterIn;
import nathol.app.enshrine.response.EmptyData;

@RestController
public final class AccountController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailMapper emailMapper;

    @Operation(description = "注册")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterIn data) {
        final User user = new User();
        user.setEmail(data.email);
        user.setUsername(data.username);
        user.setPassword(data.password);
        userMapper.insert(user);
        emailMapper.deleteById(data.code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "登录")
    @PostMapping("/login")
    public ResponseEntity<EmptyData> login(@RequestBody @Valid LoginIn data) {
        final Wrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", data.username)
                .eq("password", data.password);
        final User user = userMapper.selectOne(wrapper);
        StpUtil.login(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SaCheckLogin
    @Operation(description = "登出")
    @PostMapping("/logout")
    public ResponseEntity<EmptyData> logout() {
        StpUtil.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
