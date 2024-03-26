package nathol.app.enshrine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.validation.Valid;
import nathol.app.enshrine.handler.MailHandler;
import nathol.app.enshrine.mapper.UserMapper;
import nathol.app.enshrine.model.User;
import nathol.app.enshrine.request.EmailIn;
import nathol.app.enshrine.request.EmailUpdateIn;
import nathol.app.enshrine.response.EmptyData;

@RestController
@RequestMapping("/email")
public final class EmailController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailHandler mailHandler;

    @PostMapping
    public ResponseEntity<EmptyData> sendCode(@RequestBody @Valid EmailIn data) throws Exception {
        mailHandler.sendCode(data.email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmptyData> update(@RequestBody @Valid EmailUpdateIn data) throws Exception {
        final User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        if (!user.getEmail().equals(data.originEmail)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        mailHandler.sendCode(data.originEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
