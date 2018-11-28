package sda.sping.test1.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static int counter = 0;

    @Scheduled(fixedDelay = 10000)
    public void reportCurrentTime() {
        String[] message = {"SCHEDULED T A S K:",
                            "S-C-H-EDULED TASK:"};
        log.info(message[counter % 2] + " The time is now {}", dateFormat.format(new Date()));
        counter++;
    }
}