package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Entity;
import br.unisinos.edu.engine.domain.Event;
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
public class Preparation extends Event {

    private ClientGroup clientGroup;

    @Override
    public void execute(SchedulerService schedulerService) {
        for (int i = 0; i < clientGroup.getSize(); i++) {
            Order order = new Order(clientGroup);
            EngineRepository.queueOrders.insert(order);
            EngineRepository.entities.add(order);

            // criar evento pra enviar pedido pra mesa
        }
    }

    Entity orderEntity;


    public void executeOnStart(ClientGroup clientGroup) {
        for (int i = 0; i < clientGroup.getSize(); i++) {
            Order order = new Order(clientGroup);
            EngineRepository.queueOrders.insert(order);
            EngineRepository.entities.add(order);
        }
        if (!EngineRepository.queueOrders.isEmpty()) {
            EngineRepository.kitchen.allocate(1);
            System.out.println("Cozinha preparando pedido");
            orderEntity = EngineRepository.queueOrders.getEntityList().remove(0);
        }
    }

    public void executeOnEnd(ClientGroup clientGroup) {
        // pedido pronto, garçom entrega na mesa do grupo
        EngineRepository.kitchen.release(1);

        if (clientGroup.getStatus() == Status.WaitingInTable) {
            EngineRepository.waiter.sendWaiterToServeOrder();
            EngineRepository.waiter.setOrderAtTable();
            clientGroup.setStatus(Status.Eating);
        } else EngineRepository.queueReadyOrders.insert(orderEntity);

        // verifica fila de pedidos prontos se algum cliente (dono do pedido) já sentou na mesa, se sentou, entrega
        for (int i = 0; i < EngineRepository.queueReadyOrders.getSize(); i++) {
            if (((Order) EngineRepository.queueReadyOrders.getEntityList().get(i)).getClientGroup().getStatus() == Status.WaitingInTable) {
                EngineRepository.waiter.sendWaiterToServeOrder();
                EngineRepository.waiter.setOrderAtTable();
                clientGroup.setStatus(Status.Eating);
            }
        }
    }
}
