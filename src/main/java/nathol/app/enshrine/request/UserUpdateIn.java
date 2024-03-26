package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public final class UserUpdateIn {

    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String username = null;

    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String password = null;

    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String avator = null;

}
