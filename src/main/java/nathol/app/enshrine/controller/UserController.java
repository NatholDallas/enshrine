package nathol.app.enshrine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import nathol.app.enshrine.mapper.UserMapper;
import nathol.app.enshrine.model.User;
import nathol.app.enshrine.request.UserUpdateIn;
import nathol.app.enshrine.response.EmptyData;

@SaCheckLogin
@RestController
@RequestMapping("/user")
public final class UserController {

    @Autowired
    private UserMapper userMapper;

    @Operation(description = "检索用户")
    @GetMapping
    public ResponseEntity<User> retrieve() {
        final User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(description = "更新用户")
    @PutMapping
    public ResponseEntity<EmptyData> update(@RequestBody @Valid UserUpdateIn data) {
        final User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        if (data.username != null) {
            user.setUsername(data.username);
        }
        if (data.password != null) {
            user.setPassword(data.password);
        }
        if (data.avator != null) {
            user.setAvator(data.avator);
        }
        userMapper.updateById(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "删除账号")
    @DeleteMapping
    public ResponseEntity<EmptyData> destroy() {
        userMapper.deleteById(StpUtil.getLoginIdAsLong());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
