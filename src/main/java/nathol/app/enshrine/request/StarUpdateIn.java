package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StarUpdateIn {

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 0, max = 20)
    public final String name = null;

    @Length(min = 0, max = 20)
    public final String image = null;

    @Length(min = 0, max = 50)
    public final String description = null;

}
