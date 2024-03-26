package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import nathol.app.enshrine.annotation.EmailCode;

public final class RegisterIn {

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    public final String email = null;

    @NotNull
    @EmailCode
    public final String code = null;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String username = null;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String password = null;

}
