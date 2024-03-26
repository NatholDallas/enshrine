package nathol.app.enshrine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import jakarta.validation.Valid;
import nathol.app.enshrine.mapper.VideoMapper;
import nathol.app.enshrine.model.Video;
import nathol.app.enshrine.request.ListIn;
import nathol.app.enshrine.request.VideoIn;
import nathol.app.enshrine.request.VideoUpdateIn;
import nathol.app.enshrine.response.EmptyData;

@SaCheckLogin
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoMapper videoMapper;

    @Operation(description = "获取视频列表")
    @GetMapping
    public ResponseEntity<List<Video>> list(@ModelAttribute @Validated ListIn data) {
        final IPage<Video> page = Page.of(data.page, 20);
        final List<Video> videos = videoMapper.selectList(page, null);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @Operation(description = "创建视频")
    @PostMapping
    public ResponseEntity<Video> create(@RequestBody @Valid VideoIn data) {
        final Video video = new Video();
        video.setUser(StpUtil.getLoginIdAsLong());
        video.setTitle(data.title);
        video.setVideoUrl(data.videoUrl);
        video.setUrl(data.url);
        video.setDescription(data.description);
        videoMapper.insert(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @Operation(description = "检索视频")
    @GetMapping("/{id}")
    public ResponseEntity<Video> retrieve(@PathVariable(required = true) Long id) {
        final Video video = videoMapper.selectById(id);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @Operation(description = "更新视频")
    @PutMapping("/{id}")
    public ResponseEntity<EmptyData> update(@PathVariable(required = true) Long id,
            @RequestBody @Valid VideoUpdateIn data) {
        final Video video = videoMapper.selectById(id);
        if (data.title != null) {
            video.setTitle(data.title);
        }
        if (data.videoUrl != null) {
            video.setVideoUrl(data.videoUrl);
        }
        if (data.url != null) {
            video.setUrl(data.url);
        }
        if (data.description != null) {
            video.setDescription(data.description);
        }
        videoMapper.updateById(video);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "删除视频")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmptyData> destory(@PathVariable(required = true) Long id) {
        videoMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
