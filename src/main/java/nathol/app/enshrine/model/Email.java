package nathol.app.enshrine.model;

import lombok.Data;

@Data
public final class Email {

    private String code;

    private String email;

    private Long timestamp;

}
