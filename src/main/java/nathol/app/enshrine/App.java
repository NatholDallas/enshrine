package nathol.app.enshrine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static ApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(App.class, args);
    }

}
