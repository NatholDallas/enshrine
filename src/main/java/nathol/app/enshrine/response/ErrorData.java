package nathol.app.enshrine.response;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public final class ErrorData {

    private final int count;

    @JsonIgnoreProperties({ "codes", "arguments" })
    private final List<FieldError> fieldErrors;

    @JsonIgnoreProperties({ "codes", "arguments" })
    private final List<ObjectError> objectErrors;

    public static ErrorData of(MethodArgumentNotValidException e) {
        return new ErrorData(e.getErrorCount(), e.getFieldErrors(), e.getAllErrors());
    }

}
