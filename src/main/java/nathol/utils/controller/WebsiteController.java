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
import nathol.utils.data.Serializers.WebsiteIn;
import nathol.utils.data.Serializers.WebsiteUpdateIn;
import nathol.utils.mapper.WebsiteMapper;
import nathol.utils.model.Website;

@SaCheckLogin
@RestController
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private WebsiteMapper websiteMapper;

    @Operation(description = "获取视频列表")
    @GetMapping
    public ResponseEntity list(@ModelAttribute PageListIn data) {
        IPage<Website> page = Page.of(data.page(), 20);
        List<Website> websites = websiteMapper.selectList(page, null);
        return ResponseEntity.success(websites);
    }

    @Operation(description = "创建视频")
    @PostMapping
    public ResponseEntity create(@RequestBody WebsiteIn data) {
        Website website = new Website();
        website.setUser(StpUtil.getLoginIdAsLong());
        website.setTitle(data.title());
        website.setUrl(data.url());
        website.setDescription(data.description());
        websiteMapper.insert(website);
        return ResponseEntity.success(website);
    }

    @Operation(description = "检索视频")
    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable(required = true) Long id) {
        Website website = websiteMapper.selectById(id);
        return ResponseEntity.success(website);
    }

    @Operation(description = "更新视频")
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody WebsiteUpdateIn data, @PathVariable(required = true) Long id) {
        Website website = websiteMapper.selectById(id);
        if (data.title() != null) {
            website.setTitle(data.title());
        }
        if (data.url() != null) {
            website.setUrl(data.url());
        }
        if (data.description() != null) {
            website.setDescription(data.description());
        }
        websiteMapper.updateById(website);
        return ResponseEntity.success();
    }

    @Operation(description = "删除视频")
    @DeleteMapping("/{id}")
    public ResponseEntity destory(@PathVariable(required = true) Long id) {
        websiteMapper.deleteById(id);
        return ResponseEntity.success();
    }

}
