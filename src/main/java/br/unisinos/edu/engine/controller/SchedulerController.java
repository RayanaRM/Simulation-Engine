package br.unisinos.edu.engine.controller;

import br.unisinos.edu.engine.domain.Process;
import br.unisinos.edu.engine.domain.*;
import br.unisinos.edu.engine.service.SchedulerService;
import br.unisinos.edu.engine.settings.Mode;
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

    @PostMapping("/create-entity")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEntity(Entity entity) {
        schedulerService.createEntity(entity);
    }

    @GetMapping("/get-entity")
    @ResponseStatus(HttpStatus.OK)
    public Entity getEntity(int id) {
        return schedulerService.getEntity(id);
    }

    @PostMapping("/create-resource")
    @ResponseStatus(HttpStatus.CREATED)
    public int createResource(String name, int quantity) {
        return schedulerService.createResouce(name, quantity);
    }

    @GetMapping("/get-resource")
    @ResponseStatus(HttpStatus.OK)
    public Resource getResource(int id) {
        return schedulerService.getResource(id);
    }

    @PostMapping("/create-process")
    @ResponseStatus(HttpStatus.CREATED)
    public int createProcess(String name, double duration) {
        return schedulerService.createProcess(name, duration);
    }

    @GetMapping("/get-process")
    @ResponseStatus(HttpStatus.OK)
    public Process getProcess(int processId) {
        return schedulerService.getProcess(processId);
    }

    @PostMapping("/create-event")
    @ResponseStatus(HttpStatus.CREATED)
    public int createEvent(String name) {
        return schedulerService.createEvent(name);
    }

    @GetMapping("/get-event")
    @ResponseStatus(HttpStatus.OK)
    public Event getEvent(int eventId) {
        return schedulerService.getEvent(eventId);
    }

    @PostMapping("/create-entity-set")
    @ResponseStatus(HttpStatus.CREATED)
    public int createEntitySet(String name, Mode mode, int maxPossibleSize) {
        return schedulerService.createEntitySet(name, mode, maxPossibleSize);
    }

    @GetMapping("/get-entity-set")
    @ResponseStatus(HttpStatus.OK)
    public EntitySet getEntitySet(int id) {
        return schedulerService.getEntitySet(id);
    }
}
