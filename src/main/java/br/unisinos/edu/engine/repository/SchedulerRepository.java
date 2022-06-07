package br.unisinos.edu.engine.repository;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class SchedulerRepository {
    public static Scheduler scheduler = new Scheduler(0);

    public static List<Event> events = new ArrayList<>();
}
