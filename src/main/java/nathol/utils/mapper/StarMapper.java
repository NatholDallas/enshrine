package nathol.utils.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nathol.utils.model.Star;

@Mapper
public interface StarMapper extends BaseMapper<Star> {
}
