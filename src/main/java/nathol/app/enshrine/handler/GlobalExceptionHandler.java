package nathol.app.enshrine.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import cn.dev33.satoken.exception.NotLoginException;
import nathol.app.enshrine.response.ErrorData;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public RedirectView notLogin() {
        return new RedirectView("/login");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> invalid(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ErrorData.of(e), HttpStatus.BAD_REQUEST);
    }

}
