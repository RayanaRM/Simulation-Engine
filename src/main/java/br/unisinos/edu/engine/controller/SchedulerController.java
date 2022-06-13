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

    @GetMapping("/simulate-scheduler")
    @ResponseStatus(HttpStatus.OK)
    public void simulate() {
        schedulerService.simulate();
    }

    @GetMapping("/simulate-one-step")
    @ResponseStatus(HttpStatus.OK)
    public void simulateOneStep() {
        schedulerService.simulateOneStep();
    }

    @GetMapping("/simulate-by")
    @ResponseStatus(HttpStatus.OK)
    public void simulateBy(double duration) {
        schedulerService.simulateBy(duration);
    }

    @GetMapping("/simulate")
    @ResponseStatus(HttpStatus.OK)
    public void simulateUntil(double absoluteTime) {
        schedulerService.simulateUntil(absoluteTime);
    }
}
