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
public class KitchenAllocation extends Event {

    private Order order;

    @Override
    public boolean execute(SchedulerService schedulerService) {
        if (EngineRepository.kitchen.allocate(1)) {
            EngineRepository.queueOrders.getEntityList().remove(order);

            Preparation preparation = new Preparation(order);
            preparation.setDuration(840);

            schedulerService.scheduleIn(preparation, getDuration());
            return true;
        } else {
            schedulerService.scheduleIn(this, getDuration());
        }
        return false;
    }
}
