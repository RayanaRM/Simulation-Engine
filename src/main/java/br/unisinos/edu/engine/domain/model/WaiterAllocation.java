package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaiterAllocation extends Event {

    @Override
    public void execute(SchedulerService schedulerService) {
        if (EngineRepository.waiter.sendWaiterToCleanTable()) {
            System.out.println("Garçom limpando mesa");
            EngineRepository.waiter.setCleanTable();
        } else {
            schedulerService.scheduleIn(this, getDuration());
        }
    }

}
