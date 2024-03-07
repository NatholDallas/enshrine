package nathol.utils.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public final class InitSchedule implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(InitSchedule.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("init completed");
    }

}
