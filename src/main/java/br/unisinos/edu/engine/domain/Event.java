package br.unisinos.edu.engine.domain;

import br.unisinos.edu.engine.service.SchedulerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String name;

    private int id;

    private double time;

    private double duration;

    public void execute(SchedulerService schedulerService) {
    }

    public void executeOnStart() {
    }

    public void executeOnEnd() {
    }
}
