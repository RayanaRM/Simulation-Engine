package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Entity;
import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class Preparation extends Event {

    Entity orderEntity;


    public void executeOnStart(ClientGroup clientGroup){
        for(int i = 0; i < clientGroup.getSize(); i++){
            Order order = new Order(clientGroup);
            EngineRepository.queueOrders.insert(order, getTime());
            EngineRepository.queueOrders.setTotalPedidos(EngineRepository.queueOrders.getTotalPedidos() + 1);
            EngineRepository.entities.add(order);
        }
        if(!EngineRepository.queueOrders.isEmpty()){
            EngineRepository.kitchen.allocate(1);
            System.out.println("Cozinha preparando pedido");
            orderEntity = EngineRepository.queueOrders.getEntityList().remove(0);
        }
    }

    public void executeOnEnd(ClientGroup clientGroup){
        EngineRepository.kitchen.release(1);

        if(clientGroup.getStatus() == Status.WaitingInTable){
            EngineRepository.waiter.sendWaiterToServeOrder();
            EngineRepository.waiter.setOrderAtTable();
            clientGroup.setStatus(Status.Eating);
            EngineRepository.clientsEating.put(
                    EngineRepository.entities.stream()
                            .filter(e -> ((ClientGroup)e).getStatus() == Status.Eating)
                            .collect(Collectors.toList()).size() ,getTime());
        }

        else {
            EngineRepository.queueReadyOrders.insert(orderEntity,getTime());
            EngineRepository.queueReadyOrders.setTotalPedidos(EngineRepository.queueReadyOrders.getTotalPedidos() + 1);
        }

        for(int i = 0; i < EngineRepository.queueReadyOrders.getSize(); i++){
            if(((Order)EngineRepository.queueReadyOrders.getEntityList().get(i)).getClientGroup().getStatus() == Status.WaitingInTable){
                EngineRepository.waiter.sendWaiterToServeOrder();
                EngineRepository.waiter.setOrderAtTable();
                clientGroup.setStatus(Status.Eating);
                EngineRepository.clientsEating.put(
                        EngineRepository.entities.stream()
                                .filter(e -> ((ClientGroup)e).getStatus() == Status.Eating)
                                .collect(Collectors.toList()).size() ,getTime());
            };
        }
    }
}
