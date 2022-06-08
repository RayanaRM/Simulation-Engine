package br.unisinos.edu.engine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scheduler {
    private double time;

    public void scheduleNow(Event event){}
    public void scheduleIn(Event event, int timeToEvent){}
    public void scheduleAt(Event event, int absoluteTime){}
}
