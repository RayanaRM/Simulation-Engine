package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import br.unisinos.edu.engine.settings.Status;

public class ClientSetup extends Event {
    ClientGroup clientGroup;

    public void execute(SchedulerService schedulerService) {
        if (clientGroup.getSize() == 1) {
            EngineRepository.queueCounter.insert(clientGroup, getTime());
            EngineRepository.queueCounter.setTotalClients(EngineRepository.queueCounter.getTotalClients() + 1);

            clientGroup.setStatus(Status.WaitingInLine);

            TableAllocation tableAllocation = new TableAllocation(clientGroup, EngineRepository.counterBench);
            tableAllocation.setDuration(0);

            schedulerService.scheduleIn(tableAllocation, getDuration());
        } else if (clientGroup.getSize() > 2) {
            EngineRepository.queueTables.insert(clientGroup, getTime());
            EngineRepository.queueTables.setTotalClients(EngineRepository.queueTables.getTotalClients() + 1);

            clientGroup.setStatus(Status.WaitingInLine);

            TableAllocation tableAllocation = new TableAllocation(clientGroup, EngineRepository.tablesFourSeats);
            tableAllocation.setDuration(0);

            schedulerService.scheduleIn(tableAllocation, getDuration());
        } else {
            EngineRepository.queueTables.insert(clientGroup, getTime());
            EngineRepository.queueTables.setTotalClients(EngineRepository.queueTables.getTotalClients() + 1);

            clientGroup.setStatus(Status.WaitingInLine);

            TableAllocation tableAllocation = new TableAllocation(clientGroup, EngineRepository.tablesTwoSeats);
            tableAllocation.setDuration(0);

            schedulerService.scheduleIn(tableAllocation, getDuration());
        }
    }
}
