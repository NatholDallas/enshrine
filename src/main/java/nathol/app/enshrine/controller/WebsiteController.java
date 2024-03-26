package nathol.app.enshrine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import nathol.app.enshrine.mapper.WebsiteMapper;
import nathol.app.enshrine.model.Website;
import nathol.app.enshrine.request.ListIn;
import nathol.app.enshrine.request.WebsiteIn;
import nathol.app.enshrine.request.WebsiteUpdateIn;
import nathol.app.enshrine.response.EmptyData;

@SaCheckLogin
@RestController
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private WebsiteMapper websiteMapper;

    @Operation(description = "获取视频列表")
    @GetMapping
    public ResponseEntity<List<Website>> list(@ModelAttribute @Valid ListIn data) {
        final IPage<Website> page = Page.of(data.page, 20);
        final List<Website> websites = websiteMapper.selectList(page, null);
        return new ResponseEntity<>(websites, HttpStatus.OK);
    }

    @Operation(description = "创建视频")
    @PostMapping
    public ResponseEntity<Website> create(@RequestBody @Valid WebsiteIn data) {
        final Website website = new Website();
        website.setUser(StpUtil.getLoginIdAsLong());
        website.setTitle(data.title);
        website.setUrl(data.url);
        website.setDescription(data.description);
        websiteMapper.insert(website);
        return new ResponseEntity<>(website, HttpStatus.OK);
    }

    @Operation(description = "检索视频")
    @GetMapping("/{id}")
    public ResponseEntity<Website> retrieve(@PathVariable(required = true) Long id) {
        final Website website = websiteMapper.selectById(id);
        return new ResponseEntity<>(website, HttpStatus.OK);
    }

    @Operation(description = "更新视频")
    @PutMapping("/{id}")
    public ResponseEntity<EmptyData> update(@PathVariable(required = true) Long id,
            @RequestBody @Valid WebsiteUpdateIn data) {
        final Website website = websiteMapper.selectById(id);
        if (data.title != null) {
            website.setTitle(data.title);
        }
        if (data.url != null) {
            website.setUrl(data.url);
        }
        if (data.description != null) {
            website.setDescription(data.description);
        }
        websiteMapper.updateById(website);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "删除视频")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmptyData> destory(@PathVariable(required = true) Long id) {
        websiteMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
