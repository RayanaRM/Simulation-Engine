package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import br.unisinos.edu.engine.settings.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Preparation extends Event {

    public void executeOnStart(ClientGroup clientGroup){
        for(int i = 0; i < clientGroup.getSize(); i++){
            Order order = new Order(clientGroup.getId());
            EngineRepository.queueOrders.insert(order);
            EngineRepository.entities.add(order);
        }

        if(!EngineRepository.queueOrders.isEmpty()){
            EngineRepository.kitchen.allocate(1);
            System.out.println("Cozinha preparando pedido");
            EngineRepository.queueOrders.getEntityList().remove(0);
        }
    }

    public void executeOnEnd(ClientGroup clientGroup){
        // pedido pronto, garçom entrega na mesa do grupo
        EngineRepository.kitchen.release(1);
        EngineRepository.waiter.sendWaiterToServeOrder();
        EngineRepository.waiter.setOrderAtTable();
        clientGroup.setStatus(Status.Eating);
    }
}
