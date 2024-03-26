package nathol.app.enshrine.request;

import jakarta.validation.constraints.NotNull;
import nathol.app.enshrine.annotation.EmailCode;

public final class EmailUpdateIn {

    @EmailCode
    public final String code = null;

    @NotNull
    public final String originEmail = null;

    @NotNull
    public final String newEmail = null;

}
