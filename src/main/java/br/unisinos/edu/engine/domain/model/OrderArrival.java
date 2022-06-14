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
public class OrderArrival extends Event {

    private ClientGroup clientGroup;

    @Override
    public boolean execute(SchedulerService schedulerService) {
        for (int i = 0; i < clientGroup.getSize(); i++) {
            Order order = new Order(clientGroup);
            EngineRepository.queueOrders.insert(order, getTime());

            KitchenAllocation kitchenAllocation = new KitchenAllocation(order);
            kitchenAllocation.setDuration(0);
            schedulerService.scheduleIn(kitchenAllocation, getDuration());
        }

        return true;
    }
}
