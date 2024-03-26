package nathol.app.enshrine.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import nathol.app.enshrine.common.RandomCode;
import nathol.app.enshrine.mapper.EmailMapper;
import nathol.app.enshrine.model.Email;

@Component
public final class MailHandler {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private MailSender sender;

    public void sendCode(String email) throws Exception {
        final SimpleMailMessage message = new SimpleMailMessage();
        final String code = code();
        message.setFrom("natholdallas@qq.com");
        message.setTo(email);
        message.setSubject("enshrine——收藏自己喜欢的明星工具");
        message.setText(String.format("验证码: %s%n%s%n%s", code, "如非本人操作, 请忽略本邮件", "这是自动发送的邮件,请不要回复"));
        sender.send(message);

        final Email entity = new Email();
        entity.setCode(code);
        entity.setEmail(email);
        entity.setTimestamp(System.currentTimeMillis());
        emailMapper.insert(entity);
    }

    public String code() {
        String code = "";
        while (true) {
            code = RandomCode.code(6);
            if (emailMapper.selectById(code) != null) {
                continue;
            }
            break;
        }
        return code;
    }

}
