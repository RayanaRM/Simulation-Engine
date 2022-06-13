package br.unisinos.edu.engine.service;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.SchedulerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            double timeOfNextEvents = SchedulerRepository.events.stream()
                    .findFirst()
                    .get()
                    .getTime();

            List<Event> eventList = SchedulerRepository.events.stream()
                    .filter(event -> event.getTime() == timeOfNextEvents)
                    .collect(Collectors.toList());

            eventList.forEach(event -> event.execute(this));

            SchedulerRepository.events.removeIf(event -> event.getTime() == timeOfNextEvents);

            // some logic to add time to clock if needed, based on event success (maybe return true or false on events)
        }
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
