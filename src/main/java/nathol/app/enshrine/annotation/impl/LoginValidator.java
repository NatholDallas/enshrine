package nathol.app.enshrine.annotation.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nathol.app.enshrine.annotation.Login;
import nathol.app.enshrine.App;
import nathol.app.enshrine.mapper.UserMapper;
import nathol.app.enshrine.model.User;
import nathol.app.enshrine.request.LoginIn;

public final class LoginValidator implements ConstraintValidator<Login, LoginIn> {

    @Override
    public boolean isValid(LoginIn value, ConstraintValidatorContext context) {
        final UserMapper userMapper = App.context.getBean(UserMapper.class);
        final Wrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", value.username)
                .eq("password", value.password);
        return userMapper.exists(wrapper);
    }

}
