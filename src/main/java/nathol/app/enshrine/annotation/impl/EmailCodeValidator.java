package nathol.app.enshrine.annotation.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nathol.app.enshrine.annotation.EmailCode;
import nathol.app.enshrine.mapper.EmailMapper;
import nathol.app.enshrine.model.Email;

public final class EmailCodeValidator implements ConstraintValidator<EmailCode, String> {

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        Wrapper<Email> wrapper = new QueryWrapper<Email>()
                .eq("code", value);
        return !Objects.isNull(emailMapper.exists(wrapper));
    }

}
