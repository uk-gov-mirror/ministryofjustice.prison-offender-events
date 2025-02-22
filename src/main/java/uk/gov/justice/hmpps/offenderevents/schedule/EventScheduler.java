package uk.gov.justice.hmpps.offenderevents.schedule;

import com.microsoft.applicationinsights.TelemetryClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uk.gov.justice.hmpps.offenderevents.services.EventRetrievalService;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
@AllArgsConstructor
public class EventScheduler {

    private final EventRetrievalService eventRetrievalService;
    private final TelemetryClient telemetryClient;

    @Scheduled(
            fixedDelayString = "${application.events.poll.frequency}",
            initialDelayString = "2000")
    @SchedulerLock(name = "pollEventsLock")
    public void pollEvents() {
        log.info("Starting: Event Poll");
        try {
            eventRetrievalService.pollEvents(now());
        } catch (Exception e) {
            log.error("pollEvents: Global exception handler", e);
            telemetryClient.trackException(e);
        }
        log.info("Complete: Event Poll");
    }

}
