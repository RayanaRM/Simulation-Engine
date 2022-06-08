package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.domain.Resource;
import br.unisinos.edu.engine.repository.EngineRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Preparation extends Event {
    private EngineRepository engineRepository;


    public void executeOnStart(ClientGroup clientGroup){
        for(int i = 0; i < clientGroup.getSize(); i++)
            engineRepository.queueOrders.insert(new Order(clientGroup.getId()));

        if(!engineRepository.queueOrders.isEmpty()){
            engineRepository.kitchen.allocate(1);
            System.out.println("Cozinha preparando pedido");
            engineRepository.queueOrders.getEntityList().remove(0);
        }
    }

    public void executeOnEnd(){
        engineRepository.kitchen.release(1);
    }
}
