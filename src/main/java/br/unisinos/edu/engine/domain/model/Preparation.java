package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Entity;
import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Preparation extends Event {

    Entity orderEntity;
    public void executeOnStart(ClientGroup clientGroup){
        for(int i = 0; i < clientGroup.getSize(); i++){
            Order order = new Order(clientGroup);
            EngineRepository.queueOrders.insert(order);
            EngineRepository.entities.add(order);
        }
        if(!EngineRepository.queueOrders.isEmpty()){
            EngineRepository.kitchen.allocate(1);
            System.out.println("Cozinha preparando pedido");
            orderEntity = EngineRepository.queueOrders.getEntityList().remove(0);
        }
    }

    public void executeOnEnd(ClientGroup clientGroup){
        // pedido pronto, garçom entrega na mesa do grupo
        EngineRepository.kitchen.release(1);

        if(clientGroup.getStatus() == Status.WaitingInTable){
            EngineRepository.waiter.sendWaiterToServeOrder();
            EngineRepository.waiter.setOrderAtTable();
            clientGroup.setStatus(Status.Eating);
        }

        else EngineRepository.queueReadyOrders.insert(orderEntity);

        // verifica fila de pedidos prontos se algum cliente (dono do pedido) já sentou na mesa, se sentou, entrega
        for(int i = 0; i < EngineRepository.queueReadyOrders.getSize(); i++){
            if(((Order)EngineRepository.queueReadyOrders.getEntityList().get(i)).getClientGroup().getStatus() == Status.WaitingInTable){
                EngineRepository.waiter.sendWaiterToServeOrder();
                EngineRepository.waiter.setOrderAtTable();
                clientGroup.setStatus(Status.Eating);
            };
        }
    }
}
