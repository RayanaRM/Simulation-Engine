package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.Process;
import br.unisinos.edu.engine.domain.*;
import br.unisinos.edu.engine.repository.SchedulerRepository;
import br.unisinos.edu.engine.settings.Mode;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    public double getTime() {
        return SchedulerRepository.scheduler.getTime();
    }

    public void scheduleNow(Event event) {
        scheduleIn(event, 0);
    }

    public void scheduleIn(Event event, double timeToEvent) {
        double absoluteTime = getTime() + timeToEvent;
        scheduleAt(event, absoluteTime);
    }

    public void scheduleAt(Event event, double absoluteTime) {
        event.setTime(absoluteTime);
        SchedulerRepository.events.add(event);
    }

    public void startProcessNow(int processId) {
        // start thread for process now
    }

    public void startProcessIn(int processId, double timeToStart) {
        // start thread for process in time to start
    }

    public void startProcessAt(int processId, double absoluteTime) {
        // start thread for process in absolute time
    }

    public void waitFor(double time) {
        // sleep
    }

    public void createEntity(Entity entity) {
    }

    public Entity getEntity(int id) {
        return null;
    }

    public int createResouce(String name, int quantity) {
        return 0;
    }

    public Resource getResource(int id) {
        return null;
    }

    public int createProcess(String name, double duration) {
        return 0;
    }

    public Process getProcess(int processId) {
        return null;
    }

    public int createEvent(String name) {
        return 0;
    }

    public Event getEvent(int eventId) {
        return null;
    }

    public int createEntitySet(String name, Mode mode, int maxPossibleSize) {
        return 0;
    }

    public EntitySet getEntitySet(int id) {
        return null;
    }
}
