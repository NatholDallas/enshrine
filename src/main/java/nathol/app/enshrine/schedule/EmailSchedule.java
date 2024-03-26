package nathol.app.enshrine.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import nathol.app.enshrine.mapper.EmailMapper;

@Component
@EnableScheduling
public final class EmailSchedule {

    private final Logger logger = LoggerFactory.getLogger(EmailSchedule.class);

    @Autowired
    private EmailMapper emailMapper;

    @Scheduled(cron = "0 0 1 * * ?")
    public void run() {
        emailMapper.delete(null);
        logger.info("EmailCode map has been clear.");
    }

}
