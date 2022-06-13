package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.service.SchedulerService;
import br.unisinos.edu.engine.settings.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Preparation extends Event {

    private Order order;

    @Override
    public void execute(SchedulerService schedulerService) {
        System.out.println("Preparando pedido...");

        if (order.getClientGroup().getStatus() == Status.WaitingInTable) {
            EngineRepository.waiter.sendWaiterToServeOrder();
            EngineRepository.waiter.setOrderAtTable();

            order.getClientGroup().setStatus(Status.Eating);

            EngineRepository.clientsEating.put(
                    EngineRepository.entities.stream()
                            .filter(e -> ((ClientGroup) e).getStatus() == Status.Eating)
                            .collect(Collectors.toList()).size(), getTime());

            Eat eat = new Eat(order.getClientGroup());
            eat.setDuration(1200);
            schedulerService.scheduleIn(eat, getDuration());
        } else {
            EngineRepository.queueReadyOrders.insert(order, getTime());
            EngineRepository.queueReadyOrders.setTotalPedidos(EngineRepository.queueReadyOrders.getTotalPedidos() + 1);
        }

        for (int i = 0; i < EngineRepository.queueReadyOrders.getSize(); i++) {
            if (((Order) EngineRepository.queueReadyOrders.getEntityList().get(i)).getClientGroup().getStatus() == Status.WaitingInTable) {
                EngineRepository.waiter.sendWaiterToServeOrder();
                EngineRepository.waiter.setOrderAtTable();
                order.getClientGroup().setStatus(Status.Eating);
                EngineRepository.clientsEating.put(
                        EngineRepository.entities.stream()
                                .filter(e -> ((ClientGroup) e).getStatus() == Status.Eating)
                                .collect(Collectors.toList()).size(), getTime());
            }
        }
    }
}
