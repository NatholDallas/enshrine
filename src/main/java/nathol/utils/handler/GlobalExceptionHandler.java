package nathol.utils.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import cn.dev33.satoken.exception.NotLoginException;
import nathol.utils.data.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public RedirectView notLogin() {
        return new RedirectView("/login");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity invalid(IllegalArgumentException e) {
        return ResponseEntity.error(e);
    }

}
