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
import nathol.app.enshrine.mapper.StarMapper;
import nathol.app.enshrine.model.Star;
import nathol.app.enshrine.request.ListIn;
import nathol.app.enshrine.request.StarIn;
import nathol.app.enshrine.request.StarUpdateIn;
import nathol.app.enshrine.response.EmptyData;

@SaCheckLogin
@RestController
@RequestMapping("/star")
public final class StarController {

    @Autowired
    private StarMapper starMapper;

    @Operation(description = "获取明星列表")
    @GetMapping
    public ResponseEntity<List<Star>> list(@ModelAttribute @Valid ListIn data) {
        final IPage<Star> page = Page.of(data.page, 20);
        final List<Star> stars = starMapper.selectList(page, null);
        return new ResponseEntity<>(stars, HttpStatus.OK);
    }

    @Operation(description = "创建明星")
    @PostMapping
    public ResponseEntity<Star> create(@RequestBody @Valid StarIn data) {
        final Star star = new Star();
        star.setUser(StpUtil.getLoginIdAsLong());
        star.setName(data.name);
        star.setImage(data.image);
        star.setDescription(data.description);
        starMapper.insert(star);
        return new ResponseEntity<>(star, HttpStatus.OK);
    }

    @Operation(description = "检索明星")
    @GetMapping("/{id}")
    public ResponseEntity<Star> retrieve(@PathVariable(required = true) Long id) {
        final Star star = starMapper.selectById(id);
        return new ResponseEntity<>(star, HttpStatus.OK);
    }

    @Operation(description = "更新明星")
    @PutMapping("/{id}")
    public ResponseEntity<EmptyData> update(@PathVariable(required = true) Long id,
            @RequestBody @Valid StarUpdateIn data) {
        final Star star = starMapper.selectById(id);
        if (data.name != null) {
            star.setName(data.name);
        }
        if (data.image != null) {
            star.setImage(data.image);
        }
        if (data.description != null) {
            star.setDescription(data.description);
        }
        starMapper.updateById(star);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "删除明星")
    @DeleteMapping("/{id}")
    public ResponseEntity<EmptyData> destory(@PathVariable(required = true) Long id) {
        starMapper.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
