package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.Process;
import br.unisinos.edu.engine.domain.*;
import br.unisinos.edu.engine.repository.SchedulerRepository;
import br.unisinos.edu.engine.settings.Mode;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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

    public void simulate() {
        while (!SchedulerRepository.events.isEmpty()) {
            // sort events
//            SchedulerRepository.events.sort(Comparator.comparing(Event::getTime));
            // mandar rodar todos os eventos mais próximos (ex: todos com tempo 0)
            // getNextEvents
            // for i in nextEvents { simulateEvent }
            //simulateOneStep();
        }
    }

    public void simulateEvent(Event event) {
        // roda evento
        event.executeOnStart();
        event.executeOnEnd();
    }


    public void simulateOneStep() {
        // get current event
        Event currentEvent = SchedulerRepository.events.stream().findFirst().get();

        // execute current event
        currentEvent.executeOnStart();
        currentEvent.executeOnEnd();

        // remove current event
        SchedulerRepository.events.remove(currentEvent);

        // advance time
        SchedulerRepository.scheduler.setTime(getTime() + currentEvent.getTime());
    }

    public void simulateBy(double duration) {
        double currentRunDuration = 0;

        while (!SchedulerRepository.events.isEmpty() || currentRunDuration <= duration) {
            currentRunDuration += SchedulerRepository.events.stream().findFirst().get().getTime();
            simulateOneStep();
        }
    }

    public void simulateUntil(double absoluteTime) {
        double initialTime = getTime();
        double finalTime = getTime() + absoluteTime;

        while (!SchedulerRepository.events.isEmpty() || initialTime <= finalTime) {
            simulateOneStep();
        }
    }
}
