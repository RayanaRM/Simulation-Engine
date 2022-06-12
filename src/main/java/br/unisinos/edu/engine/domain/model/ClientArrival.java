package br.unisinos.edu.engine.domain.model;

import br.unisinos.edu.engine.domain.Event;
import br.unisinos.edu.engine.repository.EngineRepository;

import java.util.Random;

public class ClientArrival extends Event {
    private Cashier cashierUsed;
    public ClientGroup clientGroup;
    public void executeOnStart(){
        Random r = new Random();
        clientGroup = new ClientGroup(r.nextInt(4) + 1);
        EngineRepository.entities.add(clientGroup);

        if (EngineRepository.queueCashier1.getEntityList().size() <
                EngineRepository.queueCashier2.getEntityList().size()) {
            EngineRepository.queueCashier1.insert(clientGroup);
        } else {
            EngineRepository.queueCashier2.insert(clientGroup);
        }

        if(!EngineRepository.queueCashier1.getEntityList().isEmpty()){
            if(EngineRepository.cashier1.allocate(1)){
                System.out.println("Caixa 1 sendo usado");
                cashierUsed = EngineRepository.cashier1;
                EngineRepository.queueCashier1.getEntityList().remove(clientGroup);
            }
        }

        if(!EngineRepository.queueCashier2.getEntityList().isEmpty()){
            if(EngineRepository.cashier2.allocate(1)){
                System.out.println("Caixa 2 sendo usado");
                cashierUsed = EngineRepository.cashier2;
                EngineRepository.queueCashier2.getEntityList().remove(clientGroup);
            }
        }
    }

    public void executeOnEnd(){
        if (cashierUsed == EngineRepository.cashier1)
            EngineRepository.cashier1.release(1);
        else EngineRepository.cashier2.release(1);
    }
}
