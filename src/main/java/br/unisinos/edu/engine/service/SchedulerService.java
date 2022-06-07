package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.Event;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    public double getTime() {
        return 0;
    }

    public void scheduleNow(Event event) {
    }

    public void scheduleIn(Event event, double timeToEvent) {
    }

    public void scheduleAt(Event event, double absoluteTime) {
    }

    public void startProcessNow(int processId) {
    }

    public void startProcessIn(int processId, double timeToStart) {
    }

    public void startProcessAt(int processId, double absoluteTime) {
    }

    public void waitFor(double time) {
    }
}
