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
public class TableRelease extends Event {

    private ClientGroup clientGroup;

    private Resource usedTable;

    @Override
    public void execute(SchedulerService schedulerService) {
        if (usedTable == EngineRepository.counterBench)
            EngineRepository.counterBench.release(1);
        else if (usedTable == EngineRepository.tablesTwoSeats)
            EngineRepository.tablesTwoSeats.release(1);
        else EngineRepository.tablesFourSeats.release(1);

        clientGroup.setStatus(Status.Finished);
    }
}
