package nathol.app.enshrine.request;

import org.hibernate.validator.constraints.Length;

public final class WebsiteIn {

    @Length(min = 0, max = 20)
    public final String title = null;

    public final String url = null;

    @Length(min = 0, max = 50)
    public final String description = null;

}
