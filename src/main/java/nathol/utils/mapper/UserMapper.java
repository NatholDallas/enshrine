package nathol.utils.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nathol.utils.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
