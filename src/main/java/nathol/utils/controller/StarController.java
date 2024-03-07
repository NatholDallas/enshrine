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
import nathol.utils.data.Serializers.StarIn;
import nathol.utils.data.Serializers.StarUpdateIn;
import nathol.utils.mapper.StarMapper;
import nathol.utils.model.Star;

@SaCheckLogin
@RestController
@RequestMapping("/star")
public final class StarController {

    @Autowired
    private StarMapper starMapper;

    @Operation(description = "获取明星列表")
    @GetMapping
    public ResponseEntity list(@ModelAttribute PageListIn data) {
        IPage<Star> page = Page.of(data.page(), 20);
        List<Star> stars = starMapper.selectList(page, null);
        return ResponseEntity.success(stars);
    }

    @Operation(description = "创建明星")
    @PostMapping
    public ResponseEntity create(@RequestBody StarIn data) {
        Star star = new Star();
        star.setUser(StpUtil.getLoginIdAsLong());
        star.setName(data.name());
        star.setImage(data.image());
        star.setDescription(data.description());
        starMapper.insert(star);
        return ResponseEntity.success(star);
    }

    @Operation(description = "检索明星")
    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable(required = true) Long id) {
        Star star = starMapper.selectById(id);
        return ResponseEntity.success(star);
    }

    @Operation(description = "更新明星")
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody StarUpdateIn data, @PathVariable(required = true) Long id) {
        Star star = starMapper.selectById(id);
        if (data.name() != null) {
            star.setName(data.name());
        }
        if (data.image() != null) {
            star.setImage(data.image());
        }
        if (data.description() != null) {
            star.setDescription(data.description());
        }
        starMapper.updateById(star);
        return ResponseEntity.success();
    }

    @Operation(description = "删除明星")
    @DeleteMapping("/{id}")
    public ResponseEntity destory(@PathVariable(required = true) Long id) {
        starMapper.deleteById(id);
        return ResponseEntity.success();
    }

}
