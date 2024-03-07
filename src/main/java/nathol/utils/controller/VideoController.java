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
import nathol.utils.data.Serializers.VideoIn;
import nathol.utils.data.Serializers.VideoUpdateIn;
import nathol.utils.mapper.VideoMapper;
import nathol.utils.model.Video;

@SaCheckLogin
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoMapper videoMapper;

    @Operation(description = "获取视频列表")
    @GetMapping
    public ResponseEntity list(@ModelAttribute PageListIn data) {
        IPage<Video> page = Page.of(data.page(), 20);
        List<Video> videos = videoMapper.selectList(page, null);
        return ResponseEntity.success(videos);
    }

    @Operation(description = "创建视频")
    @PostMapping
    public ResponseEntity create(@RequestBody VideoIn data) {
        Video video = new Video();
        video.setUser(StpUtil.getLoginIdAsLong());
        video.setTitle(data.title());
        video.setVideoUrl(data.videoUrl());
        video.setUrl(data.url());
        video.setDescription(data.description());
        videoMapper.insert(video);
        return ResponseEntity.success(video);
    }

    @Operation(description = "检索视频")
    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable(required = true) Long id) {
        Video video = videoMapper.selectById(id);
        return ResponseEntity.success(video);
    }

    @Operation(description = "更新视频")
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody VideoUpdateIn data, @PathVariable(required = true) Long id) {
        Video video = videoMapper.selectById(id);
        if (data.title() != null) {
            video.setTitle(data.title());
        }
        if (data.videoUrl() != null) {
            video.setVideoUrl(data.videoUrl());
        }
        if (data.url() != null) {
            video.setUrl(data.url());
        }
        if (data.description() != null) {
            video.setDescription(data.description());
        }
        videoMapper.updateById(video);
        return ResponseEntity.success();
    }

    @Operation(description = "删除视频")
    @DeleteMapping("/{id}")
    public ResponseEntity destory(@PathVariable(required = true) Long id) {
        videoMapper.deleteById(id);
        return ResponseEntity.success();
    }

}
