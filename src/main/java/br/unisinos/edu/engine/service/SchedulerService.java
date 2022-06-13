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

            if (SchedulerRepository.events.removeIf(event -> event.getTime() == timeOfNextEvents)) {
                SchedulerRepository.scheduler.setTime(getTime() + timeOfNextEvents);
            }
        }
    }
}
