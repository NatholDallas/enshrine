package nathol.utils.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import nathol.utils.model.Video;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}
