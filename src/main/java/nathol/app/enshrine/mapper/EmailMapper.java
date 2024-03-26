package nathol.app.enshrine.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nathol.app.enshrine.model.Email;

@Mapper
public interface EmailMapper extends BaseMapper<Email> {
}
