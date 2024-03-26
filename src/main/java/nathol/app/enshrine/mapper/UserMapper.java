package nathol.app.enshrine.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nathol.app.enshrine.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
