package nathol.app.enshrine.request;

import jakarta.validation.constraints.Email;

public final class EmailIn {

    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    public final String email = null;

}
