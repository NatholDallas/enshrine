package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Range;

public final class ListIn {

    @Range(min = 0, max = 1000000000)
    public final Long page = 1L;

}
