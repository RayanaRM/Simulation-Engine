package br.unisinos.edu.engine.controller;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @GetMapping("/get-time")
    @ResponseStatus(HttpStatus.OK)
    public double getTime() {
        return schedulerService.getTime();
    }

    @PostMapping("/schedule-now")
    @ResponseStatus(HttpStatus.CREATED)
    public void scheduleNow(Event event) {
        schedulerService.scheduleNow(event);
    }

    @PostMapping("/schedule-in")
    @ResponseStatus(HttpStatus.CREATED)
    public void scheduleIn(Event event, double timeToEvent) {
        schedulerService.scheduleIn(event, timeToEvent);
    }

    @PostMapping("/schedule-at")
    @ResponseStatus(HttpStatus.CREATED)
    public void scheduleAt(Event event, double absoluteTime) {
        schedulerService.scheduleAt(event, absoluteTime);
    }

    @PostMapping("/start-process-now")
    @ResponseStatus(HttpStatus.CREATED)
    public void startProcessNow(int processId) {
        schedulerService.startProcessNow(processId);
    }

    @PostMapping("/start-process-in")
    @ResponseStatus(HttpStatus.CREATED)
    public void startProcessIn(int processId, double timeToStart) {
        schedulerService.startProcessIn(processId, timeToStart);
    }

    @PostMapping("/start-process-at")
    @ResponseStatus(HttpStatus.CREATED)
    public void startProcessAt(int processId, double absoluteTime) {
        schedulerService.startProcessAt(processId, absoluteTime);
    }

    @PostMapping("/wait-for")
    @ResponseStatus(HttpStatus.OK)
    public void waitFor(double time) {
        schedulerService.waitFor(time);
    }
}
