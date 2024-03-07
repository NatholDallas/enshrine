package nathol.utils.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import nathol.utils.data.ResponseEntity;
import nathol.utils.data.Serializers.PageListIn;
import nathol.utils.data.Serializers.UserUpdateIn;
import nathol.utils.mapper.UserMapper;
import nathol.utils.model.User;

@SaCheckLogin
@RestController
@RequestMapping("/user")
public final class UserController {

    @Autowired
    private UserMapper userMapper;

    @Operation(description = "用户列表")
    @PostMapping
    public ResponseEntity list(@ModelAttribute PageListIn data) {
        IPage<User> page = Page.of(data.page(), 20);
        List<User> users = userMapper.selectList(page, null);
        return ResponseEntity.success(users);
    }

    @Operation(description = "检索用户")
    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable(required = true) Long id) {
        User user = userMapper.selectById(id);
        return ResponseEntity.success(user);
    }

    @Operation(description = "更新用户")
    @PutMapping
    public ResponseEntity update(@RequestBody UserUpdateIn data) {
        User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        if (data.email() != null) {
            user.setEmail(data.email());
        }
        if (data.username() != null) {
            user.setUsername(data.username());
        }
        if (data.password() != null) {
            user.setPassword(data.password());
        }
        userMapper.updateById(user);
        return ResponseEntity.success();
    }

    @Operation(description = "删除账号")
    @DeleteMapping
    public ResponseEntity destroy() {
        userMapper.deleteById(StpUtil.getLoginIdAsLong());
        return ResponseEntity.success();
    }

}
