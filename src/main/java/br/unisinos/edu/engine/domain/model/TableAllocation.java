package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import br.unisinos.edu.engine.settings.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableAllocation extends Event {

    private ClientGroup clientGroup;
    Resource resource;

    @Override
    public void execute(SchedulerService schedulerService) {
        if (resource.allocate(1)) {
            WaiterAllocation waiterAllocation = new WaiterAllocation();
            waiterAllocation.setDuration(0);
            schedulerService.scheduleIn(waiterAllocation, getDuration());

            System.out.println("Mesa alocada para quantidade de clientes " + clientGroup.getSize());
            clientGroup.setStatus(Status.WaitingInTable);

            if (clientGroup.getSize() > 2)
                EngineRepository.queueTables.getEntityList().remove(clientGroup);
            else
                EngineRepository.queueCounter.getEntityList().remove(clientGroup);

            TableRelease tableRelease = new TableRelease(clientGroup, resource);
            tableRelease.setDuration(0);
            schedulerService.scheduleIn(tableRelease, getDuration());
        } else {
            schedulerService.scheduleIn(this, getDuration());
        }
    }

}
