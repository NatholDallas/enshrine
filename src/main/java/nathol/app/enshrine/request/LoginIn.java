package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import nathol.app.enshrine.annotation.Login;

@Login
public final class LoginIn {

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
