package nathol.utils.data;

public final class Serializers {

    public record PageListIn(Long page) {
    }

    public record LoginIn(String username, String password) {
    }

    public record RegisterIn(String email, String username, String password) {
    }

    public record UserUpdateIn(String email, String username, String password, String avator) {
    }

    public record StarIn(String name, String image, String description) {
    }

    public record StarUpdateIn(String name, String image, String description) {
    }

    public record VideoIn(String title, String videoUrl, String url, String description) {
    }

    public record VideoUpdateIn(String title, String videoUrl, String url, String description) {
    }

    public record WebsiteIn(String title, String url, String description) {
    }

    public record WebsiteUpdateIn(String title, String url, String description) {
    }

}
